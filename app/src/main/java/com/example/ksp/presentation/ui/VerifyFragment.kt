package com.example.ksp.presentation.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ksp.R
import com.example.ksp.data.model.modelrequest.NewPasswordRequest
import com.example.ksp.data.model.modelrequest.VerifyRequest
import com.example.ksp.data.util.Utils.validateNewPasswordRequest
import com.example.ksp.data.util.Utils.validateVerifyRequest
import com.example.ksp.databinding.FragmentLoginBinding
import com.example.ksp.databinding.FragmentVerifyBinding
import com.example.ksp.presentation.viewmodel.VerifyViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VerifyFragment : Fragment() {

    val viewModel: VerifyViewModel by viewModels()
    private lateinit var verifyBinding: FragmentVerifyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentVerifyBinding.inflate(inflater, container, false)
        verifyBinding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        verifyBinding.verifyButton.setOnClickListener {

            hideSoftKeyboard()

            val phone = verifyBinding.verifyPhoneEditText.editableText.toString()
            val dob = verifyBinding.verifyDobEditText.editableText.toString()
            val ic = verifyBinding.verifyIcEditText.editableText.toString().toInt()

            val result = validateVerifyRequest(phone, dob, ic)

            if(result.successful){
                verifyBinding.verifyProgress.loadingProgress.visibility = View.VISIBLE
                verifyBinding.verifyButton.isEnabled = false

                val verifyRequest = VerifyRequest(phone, dob, ic)

                viewModel.verify(verifyRequest = verifyRequest)

                viewModel.successful.observe(viewLifecycleOwner){ successful ->
                    if(successful == true){
                        verifyBinding.verifyProgress.loadingProgress.visibility = View.GONE
                        verifyBinding.verifyButton.visibility = View.GONE
                        verifyBinding.verifyPhoneEditText.isEnabled = false
                        verifyBinding.verifyDobEditText.isEnabled = false
                        verifyBinding.verifyIcEditText.isEnabled = false
                        verifyBinding.updatePasswordLayout.visibility = View.VISIBLE
                        Log.d("MainActivity", "VerfiyFragment -> DATA SENT $verifyRequest")
                        viewModel.toNewSection()
                    } else if(successful == false){
                        verifyBinding.verifyProgress.loadingProgress.visibility = View.GONE
                        verifyBinding.verifyButton.isEnabled = true
                        Snackbar.make(verifyBinding.verifyButton, "${viewModel.error.value}", Snackbar.LENGTH_SHORT).show()
                        viewModel.toNewSection()
                    }
                }
            } else {
                Snackbar.make(verifyBinding.verifyButton, "${result.error}", Snackbar.LENGTH_SHORT).show()
            }
        }

        verifyBinding.newPswdConfirmButton.setOnClickListener {

            hideSoftKeyboard()

            val phone = verifyBinding.verifyPhoneEditText.editableText.toString()
            val dob = verifyBinding.verifyDobEditText.editableText.toString()
            val ic = verifyBinding.verifyIcEditText.editableText.toString().toInt()
            val pswd1 = verifyBinding.newPasswordEditText.editableText.toString()
            val pswd2 = verifyBinding.newPasswordConfirmationEditText.editableText.toString()

            val result = validateNewPasswordRequest(phone, dob, ic, pswd1, pswd2)

            if(result.successful){
                verifyBinding.verifyProgress.loadingProgress.visibility = View.VISIBLE
                verifyBinding.newPswdConfirmButton.isEnabled = false

                val newPasswordRequest = NewPasswordRequest(pswd1, phone, dob, ic)

                viewModel.updatePassword(newPasswordRequest = newPasswordRequest)

                viewModel.successful.observe(viewLifecycleOwner){ successful ->
                    if(successful == true){
                        verifyBinding.verifyProgress.loadingProgress.visibility = View.GONE
                        verifyBinding.newPswdConfirmButton.isEnabled = true
                        findNavController().navigate(R.id.action_verifyFragment_to_loginFragment)
                        Log.d("MainActivity", "VerfiyFragment -> DATA SENT $newPasswordRequest")
                        viewModel.toNewSection()
                    } else if(successful == false){
                        verifyBinding.verifyProgress.loadingProgress.visibility = View.GONE
                        verifyBinding.newPswdConfirmButton.isEnabled = true
                        Snackbar.make(verifyBinding.verifyButton, "${viewModel.error.value}", Snackbar.LENGTH_SHORT).show()
                        viewModel.toNewSection()
                    }
                }
            } else {
                Snackbar.make(verifyBinding.verifyButton, "${result.error}", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun hideSoftKeyboard(){
        // Get the input method manager in fragments of onViewCreated need requireActivity()
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}