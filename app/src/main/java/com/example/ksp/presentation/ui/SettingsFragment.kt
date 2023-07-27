package com.example.ksp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ksp.R
import com.example.ksp.databinding.FragmentSettingsBinding
import com.example.ksp.presentation.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    val viewModel: SettingsViewModel by viewModels()
    private lateinit var settingsBinding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsBinding = FragmentSettingsBinding.inflate(layoutInflater)

        return settingsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // data bidding
        settingsBinding.settingsFragment = this
    }

    fun checkUserLoggedIn(){
        if(!settingsBinding.keepLoggedIn.isChecked){
            viewModel.unSaveUserLoggedIn()
            //viewModel.unCheckUserKeepLoggedIn()
            settingsBinding.keepLoggedIn.isChecked = false
        }
        else if (settingsBinding.keepLoggedIn.isChecked) {
            viewModel.saveUserLoggedIn()
            //viewModel.checkUserKeepLoggedIn()
            settingsBinding.keepLoggedIn.isChecked = true
        }
    }

/*    fun logout(){
        viewModel.unSaveUserLoggedIn()
        findNavController().navigate(R.id.action_settingsFragment_to_loginFragment)
    }
*/
}