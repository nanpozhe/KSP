package com.example.ksp.presentation.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.ksp.R
import com.example.ksp.R.*
import com.example.ksp.databinding.FragmentParkPayBinding
import com.example.ksp.presentation.viewmodel.ParkPayViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ParkPayFragment : Fragment() {

    companion object {
        fun newInstance() = ParkPayFragment()
    }

    val viewModel: ParkPayViewModel by viewModels()
    private var parkPayBinding: FragmentParkPayBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentParkPayBinding.inflate(inflater, container, false)
        parkPayBinding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parkPayBinding?.parkPayFragment = this

        parkPayBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
            parkPayFragment = this@ParkPayFragment
        }

        // get council name from app pref and display
        parkPayBinding?.councilSelectedValue?.text = viewModel.getCouncilSelectedName()
        // get car plate no from app pref and display
        parkPayBinding?.vehicleSelectedValue?.text = viewModel.getCarPlateSelected()
    }

    // function to create setDuration, parkNow->pop up a confirmation dialog
    fun setDurationAmount(d: Int, a: Double, view: View){
        viewModel.setDurationAmount(d, a)
        val btnArray: Array<MaterialButton> = arrayOf(parkPayBinding?.min30Button!!,
            parkPayBinding?.hr1Button!!, parkPayBinding?.hr1Min30Button!!, parkPayBinding?.hr2Button!!,
            parkPayBinding?.hr2Min30Button!!, parkPayBinding?.hr3Button!!, parkPayBinding?.hr3Min30Button!!,
            parkPayBinding?.hr4Button!!, parkPayBinding?.oneDayButton!!)
        for (i in 0..8){
            Log.d("MainActivity", "PPFragment -> $i + ${btnArray[i].id} + ${view.id}")
            if(view.id == btnArray[i].id){
                setButtonChecked(view)
            } else {
                setButtonUnchecked(btnArray[i]!!)
            }
        }
    }

    fun payNow(){
        val alert1 = "Details of Parking"
        val alert2 = "Council : ${viewModel.getCouncilSelectedName()}"
        val alert3 = "Vehicle : ${viewModel.getCarPlateSelected()}"
        val alert4 = "Duration : ${viewModel.duration.value} minutes"
        val alert5 = "Total : RM${viewModel.amount.value}"
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Payment Confirmation")
            .setMessage(alert1+"\n"+alert2+"\n"+alert3+"\n"+alert4+"\n"+alert5)
            .setNegativeButton("Cancel"){ dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton("Confirm"){ dialog, which ->
                dialog.dismiss()
                parkPayBinding?.loginProgress?.loadingProgress?.visibility = View.VISIBLE
                parkPayBinding?.parkNowButton?.isEnabled = false
                viewModel.parkNPay()
                viewModel.successful.observe(viewLifecycleOwner){ successful ->
                    if(successful == true){
                        parkPayBinding?.loginProgress?.loadingProgress?.visibility = View.INVISIBLE
                        parkPayBinding?.parkNowButton?.isEnabled = true
                        Snackbar.make(requireView(), "Successfully parked", Snackbar.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_parkPayFragment_to_homeFragment)
                    } else if(successful == false){
                        parkPayBinding?.loginProgress?.loadingProgress?.visibility = View.INVISIBLE
                        parkPayBinding?.parkNowButton?.isEnabled = true
                        Snackbar.make(requireView(), "${viewModel.error.value}", Snackbar.LENGTH_LONG).show()
                    }
                }
            }
            .show()
    }

    fun setButtonChecked(id: View){
        (id as Button).setTextColor(resources.getColor(color.white))
        id.setBackgroundColor(resources.getColor(color.purple_500))
    }

    fun setButtonUnchecked(id: MaterialButton){
        id.setTextColor(resources.getColor(color.grey))
        id.setBackgroundColor(resources.getColor(color.white))
    }
}