package com.example.ksp.presentation.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.ksp.R
import com.example.ksp.databinding.FragmentHomeBinding
import com.example.ksp.presentation.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance(): HomeFragment{
            return HomeFragment()
        }
    }

    private lateinit var homeBinding: FragmentHomeBinding

    @Inject
    lateinit var homeViewModel: HomeViewModel

    // variable for timer
    val MINUTES_TO_MILLI_SECONDS = 60000L
    lateinit var countdown_timer: CountDownTimer
    var time_in_milli_seconds = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        time_in_milli_seconds = homeViewModel.getParkingDuration().toLong() * MINUTES_TO_MILLI_SECONDS
        startTimer(time_in_milli_seconds)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)

        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // data bidding
        homeBinding.homeFragment = this

        homeBinding.userFullName.text = homeViewModel.getUserName()

        // load wallet id
        homeViewModel.getWalletId()
        disablePage()
        homeViewModel.walletIdSuccessful.observe(viewLifecycleOwner){ successful ->
            if(successful == true){
                homeViewModel.proceedToGetBalance()
                homeViewModel.getWalletBalance()
                disablePage()
                // check to set the wallet balance on the home page
                homeViewModel.balanceSuccessful.observe(viewLifecycleOwner){ successful ->
                    if(successful == true){
                        enablePage()
                        homeBinding.ewalletBalance.text = "RM ${homeViewModel.walletBalance}"
                        homeViewModel.navigateToPage()
                    } else if (successful == false){
                        Snackbar.make(view, "${homeViewModel.balanceError.value}", Snackbar.LENGTH_LONG).show()
                        homeViewModel.navigateToPage()
                        //findNavController().navigate(R.id.action_topUpFragment_to_homeFragment)
                    }
                }
            } else if(successful == false){
                Snackbar.make(view, "${homeViewModel.walletIdError.value}", Snackbar.LENGTH_LONG).show()
                enablePage()
            }
        }

        homeBinding.parkingStatusValue.text = homeViewModel.getParkingStatus()
    }

    fun toSettingsPage(){
        findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
    }

    fun toParkNPayPage(){
        findNavController().navigate(R.id.action_homeFragment_to_parkPayFragment)
    }

    fun toCouncilPage(){
        findNavController().navigate(R.id.action_homeFragment_to_councilFragment)
    }

    fun toTopUpPage(){
        findNavController().navigate(R.id.action_homeFragment_to_topUpFragment)
    }

    fun toVehiclePage(){
        findNavController().navigate(R.id.action_homeFragment_to_carFragment)
    }

    fun toSeasonPassPage(){
        findNavController().navigate(R.id.action_homeFragment_to_seasonPassFragment)
    }

    private fun disablePage(){
        homeBinding.loginProgress.loadingProgress.visibility = View.VISIBLE
        homeBinding.statusLayout.isEnabled = false
        homeBinding.actionsLayout.isEnabled = false
    }

    private fun enablePage(){
        homeBinding.loginProgress.loadingProgress.visibility = View.INVISIBLE
        homeBinding.statusLayout.isEnabled = true
        homeBinding.actionsLayout.isEnabled = true
    }

    /** timer function **/
    fun startTimer(time_in_minutes: Long){
        countdown_timer = object : CountDownTimer(time_in_minutes, 60000){
            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
                updateTextUI()
            }
            override fun onFinish() {
                homeViewModel.resetParking()
            }
        }
        countdown_timer.start()
    }

    private fun updateTextUI(){
        val minute = (time_in_milli_seconds / 1000) / 60
        //val seconds = (time_in_milli_seconds / 1000) % 60

        homeBinding.timeRemainingValue.text = "$minute minutes"
    }
}