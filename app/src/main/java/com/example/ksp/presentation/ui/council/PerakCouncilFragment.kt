package com.example.ksp.presentation.ui.council

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import com.example.ksp.R
import com.example.ksp.databinding.FragmentPerakCouncilBinding
import com.example.ksp.presentation.viewmodel.council.PerakCouncilViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PerakCouncilFragment : Fragment() {

    companion object {
        fun newInstance() = PerakCouncilFragment()
    }

    val viewModel: PerakCouncilViewModel by viewModels()
    private lateinit var perakCouncilBinding: FragmentPerakCouncilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPerakCouncilBinding.inflate(inflater, container, false)
        perakCouncilBinding = fragmentBinding
        return fragmentBinding.root
    }

    fun onRadioButtonClicked(view: View){
        if(view is RadioButton){
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.ipoh_btn ->
                    if (checked) {
                        viewModel.saveCouncil(id = R.id.ipoh_btn, name = resources.getString(R.string.ipoh))
                    }
                R.id.kampar_btn ->
                    if (checked) {
                        viewModel.saveCouncil(id = R.id.kampar_btn, name = resources.getString(R.string.kampar))
                    }
                R.id.taiping_btn ->
                    if (checked) {
                        viewModel.saveCouncil(id = R.id.taiping_btn, name = resources.getString(R.string.taiping))
                    }
                R.id.manjung_btn ->
                    if (checked) {
                        viewModel.saveCouncil(id = R.id.manjung_btn, name = resources.getString(R.string.manjung))
                    }
                R.id.teluk_intan_btn ->
                    if (checked) {
                        viewModel.saveCouncil(id = R.id.teluk_intan_btn, name = resources.getString(R.string.telukIntan))
                    }
                R.id.batu_gajah_btn ->
                    if (checked) {
                        viewModel.saveCouncil(id = R.id.batu_gajah_btn, name = resources.getString(R.string.batuGajah))
                    }
                R.id.gerik_btn ->
                    if (checked) {
                        viewModel.saveCouncil(id = R.id.gerik_btn, name = resources.getString(R.string.gerik))
                    }
                R.id.kuala_kangsar_btn ->
                    if (checked) {
                        viewModel.saveCouncil(id = R.id.kuala_kangsar_btn, name = resources.getString(R.string.kualaKangsar))
                    }
                R.id.kerian_btn ->
                    if (checked) {
                        viewModel.saveCouncil(id = R.id.kerian_btn, name = resources.getString(R.string.kerian))
                    }
                R.id.perak_tengah_btn ->
                    if (checked) {
                        viewModel.saveCouncil(id = R.id.perak_tengah_btn, name = resources.getString(R.string.perakTengah))
                    }
                R.id.tanjong_malim_btn ->
                    if (checked) {
                        viewModel.saveCouncil(id = R.id.tanjong_malim_btn, name = resources.getString(R.string.tanjongMalim))
                    }
                R.id.tapah_btn ->
                    if (checked) {
                        viewModel.saveCouncil(id = R.id.tapah_btn, name = resources.getString(R.string.tapah))
                    }
                R.id.selama_btn ->
                    if (checked) {
                        viewModel.saveCouncil(id = R.id.selama_btn, name = resources.getString(R.string.selama))
                    }
            }
            val name = viewModel.getCouncilName()
            val id = viewModel.getCouncilId()
            Log.d("MainActivity", "Perak fragment -> $name && $id")
            Snackbar.make(view, "$name is selected.", Snackbar.LENGTH_LONG).show()
        }
    }
}