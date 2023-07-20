package com.example.ksp.presentation.ui

import android.content.Context
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ksp.R
import com.example.ksp.data.model.modelrequest.LoginRequest
import com.example.ksp.data.util.Utils.validateLoginRequest
import com.example.ksp.databinding.FragmentLoginBinding
import com.example.ksp.presentation.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    val viewModel: LoginViewModel by viewModels()
    private lateinit var loginBinding: FragmentLoginBinding
    val homeFragment = HomeFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentLoginBinding.inflate(inflater, container, false)
        loginBinding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // data bidding
        loginBinding.loginFragment = this

        // login button onclick listener
        loginBinding.loginButton.setOnClickListener {

            hideSoftKeyboard()

            val phone = loginBinding.phoneEditText.text.toString()
            val password = loginBinding.loginPasswordEditText.text.toString()

            val result = validateLoginRequest(phone, password)

            if(result.successful) {
                loginBinding.loginProgress.loadingProgress.visibility = View.VISIBLE
                loginBinding.loginButton.isEnabled = false
                loginBinding.signUpNow.isEnabled = false

                val loginRequest = LoginRequest(phone, password)

                viewModel.login(loginRequest = loginRequest)

                viewModel.successful.observe(viewLifecycleOwner){ successful ->
                    if(successful == true){
                        loginBinding.loginProgress.loadingProgress.visibility = View.GONE
                        loginBinding.loginButton.isEnabled = true
                        loginBinding.signUpNow.isEnabled = true
                        Log.d("MainActivity","LoginFragment -> DATA SENT $loginRequest")
                        //findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView1, homeFragment)
                            .addToBackStack(null)
                            .commit()
                        viewModel.navigateToPage()
                    } else if (successful == false){
                        loginBinding.loginProgress.loadingProgress.visibility = View.GONE
                        loginBinding.loginButton.isEnabled = true
                        loginBinding.signUpNow.isEnabled = true
                        Snackbar.make(loginBinding.loginButton, "${viewModel.error.value}", Snackbar.LENGTH_SHORT).show()
                        viewModel.navigateToPage()
                    }
                }
            } else {
                Snackbar.make(loginBinding.loginButton, "${result.error}", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    fun navigateToSignUp(){
        findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
    }

    fun navigateToVerify(){
        findNavController().navigate(R.id.action_loginFragment_to_verifyFragment)
    }

    private fun hideSoftKeyboard(){
        // Get the input method manager in fragments of onViewCreated need requireActivity()
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}