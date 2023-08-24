package com.example.ksp.presentation.ui

import android.content.Intent
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private val viewModel: SettingsViewModel by viewModels()
    private var settingsBinding: FragmentSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        settingsBinding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // data bidding
        settingsBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
            settingsFragment = this@SettingsFragment
        }



        initialCheckOnUserLoggedInToggle()
    }

    fun initialCheckOnUserLoggedInToggle(){
        viewModel.initCheckUserLoggedInToggle()
    }

    fun checkUserLoggedIn(){
        if(settingsBinding?.keepLoggedIn?.isChecked == false){
            viewModel.unSaveUserLoggedIn()
            viewModel.changeUserKeepLoggedIn(choice = false)
            settingsBinding?.keepLoggedIn?.isChecked = false
        }
        else if (settingsBinding?.keepLoggedIn?.isChecked == true) {
            viewModel.changeUserKeepLoggedIn(choice = true)
            settingsBinding?.keepLoggedIn?.isChecked = true
        }
    }

    fun logout(){
        viewModel.unSaveUserLoggedIn()
        val intent = Intent(context, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        context?.startActivity(intent)
    }

    fun clearCache(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.clear_cache_dialog_title))
            .setMessage(resources.getString(R.string.clear_cache_dialog_message))
            .setNegativeButton("Cancel"){ dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Confirm"){ dialog, which ->
                val clr = viewModel.reset()
                if(clr.successful){
                    Snackbar.make(requireView(), "${clr.error}", Snackbar.LENGTH_LONG).show()
                } else {
                    Snackbar.make(requireView(), "${clr.error}", Snackbar.LENGTH_LONG).show()
                }
                dialog.dismiss()
            }
            .show()
    }

}