package com.example.ksp.presentation.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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

        homeBinding.userFullName.text = homeViewModel.username

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
}