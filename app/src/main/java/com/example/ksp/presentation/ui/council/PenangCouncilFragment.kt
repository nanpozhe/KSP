package com.example.ksp.presentation.ui.council

import PenangCouncilViewModel
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import com.example.ksp.R
import com.example.ksp.databinding.FragmentPenangCouncilBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PenangCouncilFragment : Fragment() {

    companion object {
        fun newInstance() = PenangCouncilFragment()
    }

    val viewModel: PenangCouncilViewModel by viewModels()
    private lateinit var penangCouncilBinding: FragmentPenangCouncilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPenangCouncilBinding.inflate(inflater, container, false)
        penangCouncilBinding = fragmentBinding
        return fragmentBinding.root
    }

    fun onRadioButtonClicked(view: View){
        if(view is RadioButton){
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.mbpp_btn ->
                    if (checked) {
                        viewModel.saveCouncil(id = R.id.mbpp_btn, name = resources.getString(R.string.mbpp))
                    }
                R.id.mbsp_btn ->
                    if (checked) {
                        viewModel.saveCouncil(id = R.id.mbsp_btn, name = resources.getString(R.string.mbsp))
                    }
            }
            val name = viewModel.getCouncilName()
            val id = viewModel.getCouncilId()
            Log.d("MainActivity", "Penang fragment -> $name && $id")
            Snackbar.make(view, "$name is selected.", Snackbar.LENGTH_LONG).show()
        }
    }
}