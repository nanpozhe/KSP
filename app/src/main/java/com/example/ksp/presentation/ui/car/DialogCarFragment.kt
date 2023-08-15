package com.example.ksp.presentation.ui.car

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.ksp.databinding.DialogCarBinding
import com.example.ksp.presentation.viewmodel.CarViewModel
import com.google.android.material.snackbar.Snackbar

class DialogCarFragment : DialogFragment() {

    val sharedViewModel: CarViewModel by activityViewModels()
    private var carDialogBinding: DialogCarBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = DialogCarBinding.inflate(inflater, container, false)
        carDialogBinding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carDialogBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
        }

        carDialogBinding?.confirmCarDialogButton?.setOnClickListener {
            hideSoftKeyboard()

            carDialogBinding!!.confirmCarDialogButton.isEnabled = false
            carDialogBinding!!.loginProgress.loadingProgress.visibility = View.VISIBLE

            val carPlate = carDialogBinding!!.carPlateEditText.text.toString()
            val carColor = carDialogBinding!!.carColorEditText.text.toString()
            val carBrand = carDialogBinding!!.carBrandEditText.text.toString()

            sharedViewModel.createCar(carPlate, carColor, carBrand)

            sharedViewModel.successful.observe(viewLifecycleOwner){ successful ->
                if(successful == true){
                    carDialogBinding!!.confirmCarDialogButton.isEnabled = true
                    carDialogBinding!!.loginProgress.loadingProgress.visibility = View.GONE
                    sharedViewModel.resetIndicator()
                    dialog?.dismiss()
                } else if (successful == false){
                    carDialogBinding!!.confirmCarDialogButton.isEnabled = true
                    carDialogBinding!!.loginProgress.loadingProgress.visibility = View.GONE
                    Snackbar.make(view, "${sharedViewModel.error.value}", Snackbar.LENGTH_LONG).show()
                    sharedViewModel.resetIndicator()
                    dialog?.dismiss()
                }
            }
        }
    }

    private fun hideSoftKeyboard(){
        // Get the input method manager in fragments of onViewCreated need requireActivity()
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}