package com.example.ksp.presentation.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import com.example.ksp.R
import com.example.ksp.databinding.FragmentUpdtPswdBinding
import com.example.ksp.databinding.FragmentVerifyBinding
import com.example.ksp.presentation.viewmodel.UpdtPswdViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * no using this fragment
  */

@AndroidEntryPoint
class UpdtPswdFragment : Fragment() {

    val viewModel: UpdtPswdViewModel by viewModels()
    private lateinit var updtPswdBinding: FragmentUpdtPswdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentUpdtPswdBinding.inflate(inflater, container, false)
        updtPswdBinding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updtPswdBinding.newPswdConfirmButton.setOnClickListener {

            hideSoftKeyboard()


        }
    }

    private fun hideSoftKeyboard(){
        // Get the input method manager in fragments of onViewCreated need requireActivity()
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}