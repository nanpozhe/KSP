package com.example.ksp.presentation.ui.council

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ksp.R
import com.example.ksp.databinding.FragmentSelangorCouncilBinding
import com.example.ksp.presentation.viewmodel.CouncilViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelangorCouncilFragment : Fragment() {

    companion object {
        fun newInstance() = SelangorCouncilFragment()
    }

    private val sharedViewModel: CouncilViewModel by activityViewModels()
    private var selangorCouncilBinding: FragmentSelangorCouncilBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSelangorCouncilBinding.inflate(inflater, container, false)
        selangorCouncilBinding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        selangorCouncilBinding?.apply { 
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            selangorCouncilFragment = this@SelangorCouncilFragment
        }
    }

    fun onRadioButtonClicked() {
        /*if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.subang_jaya_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.subang_jaya_btn, name = resources.getString(R.string.subangJaya))
                    }
                R.id.petaling_jaya_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.petaling_jaya_btn, name = resources.getString(R.string.petalingJaya))
                    }
                R.id.shah_alam_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.shah_alam_btn, name = resources.getString(R.string.shahAlam))
                    }
                R.id.kajang_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.kajang_btn, name = resources.getString(R.string.kajang))
                    }
                R.id.ampang_jaya_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.ampang_jaya_btn, name = resources.getString(R.string.ampangJaya))
                    }
                R.id.selayang_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.selayang_btn, name = resources.getString(R.string.selayang))
                    }
                R.id.sepang_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.sepang_btn, name = resources.getString(R.string.sepang))
                    }
                R.id.klang_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.klang_btn, name = resources.getString(R.string.klang))
                    }
                R.id.kuala_selangor_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.kuala_selangor_btn, name = resources.getString(R.string.kualaLangat))
                    }
                R.id.kuala_langat_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.kuala_langat_btn, name = resources.getString(R.string.kualaLangat))
                    }
            }*/
            sharedViewModel.saveCouncil()
            val name = sharedViewModel.getCouncilNameFromApp()
            val id = sharedViewModel.getCouncilIdFromApp()
            Log.d("MainActivity", "Perak fragment -> $name && $id")
            Snackbar.make(requireView(), "$name is selected.", Snackbar.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_selangorCouncilFragment_to_councilFragment)
        }
    override fun onDestroyView() {
        super.onDestroyView()
        selangorCouncilBinding = null
    }
}