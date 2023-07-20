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
import com.example.ksp.data.model.modelrequest.RegisterRequest
import com.example.ksp.data.util.Utils.validateRegisterRequest
import com.example.ksp.databinding.FragmentLoginBinding
import com.example.ksp.databinding.FragmentSignUpBinding
import com.example.ksp.presentation.viewmodel.SignUpViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    val viewModel: SignUpViewModel by viewModels()
    private lateinit var signUpBinding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        signUpBinding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // data bidding
        signUpBinding.signUpFragment = this

        // register button onclick listener
        signUpBinding.registerButton.setOnClickListener {

            hideSoftKeyboard()

            val fullname = signUpBinding.registerNameEditText.text.toString()
            val phone = signUpBinding.registerPhoneEditText.text.toString()
            val dob = signUpBinding.registerDobEditText.text.toString()
            val ic = signUpBinding.registerIcEditText.text.toString().toInt()
            val email = signUpBinding.registerEmailEditText.text.toString()
            val pswd = signUpBinding.registerPasswordEditText.text.toString()
            val pswdConfirm = signUpBinding.registerPasswordConfirmationEditText.text.toString()

            val result = validateRegisterRequest(fullname, phone, dob, ic, email, pswd, pswdConfirm)

            if (result.successful){
                signUpBinding.registerProgress.loadingProgress.visibility = View.VISIBLE
                signUpBinding.registerButton.isEnabled = false

                val registerRequest = RegisterRequest(fullname, phone, dob, ic, email, pswd)

                viewModel.register(registerRequest = registerRequest)

                viewModel.successful.observe(viewLifecycleOwner) { successful ->
                    if(successful == true){
                        signUpBinding.registerProgress.loadingProgress.visibility = View.GONE
                        signUpBinding.registerButton.isEnabled = true
                        Log.d("MainActivity","SignUpFragment -> DATA SENT $registerRequest")
                        findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
                        viewModel.navigateToPage()
                    } else {
                        signUpBinding.registerProgress.loadingProgress.visibility = View.GONE
                        signUpBinding.registerButton.isEnabled = true
                        Snackbar.make(signUpBinding.registerButton, "${viewModel.error.value}", Snackbar.LENGTH_SHORT).show()
                        viewModel.navigateToPage()
                    }
                }
            } else {
                Snackbar.make(signUpBinding.registerButton, "${result.error}", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun hideSoftKeyboard(){
        // Get the input method manager in fragments of onViewCreated need requireActivity()
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}