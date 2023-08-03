package com.example.ksp.presentation.ui.council

import PenangCouncilViewModel
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ksp.R
import com.example.ksp.databinding.FragmentPenangCouncilBinding
import com.example.ksp.presentation.viewmodel.CouncilViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PenangCouncilFragment : Fragment() {

    companion object {
        fun newInstance() = PenangCouncilFragment()
    }

    private val sharedViewModel: CouncilViewModel by activityViewModels()
    private var penangCouncilBinding: FragmentPenangCouncilBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPenangCouncilBinding.inflate(inflater, container, false)
        penangCouncilBinding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        penangCouncilBinding?.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            // Assign the view model to a property in the binding class
            viewModel = sharedViewModel

            // Assign the fragment
            penangCouncilFragment = this@PenangCouncilFragment
        }
    }

    fun onRadioButtonClicked(){
        /*if(view is RadioButton){
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.mbpp_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.mbpp_btn, name = resources.getString(R.string.mbpp))
                    }
                R.id.mbsp_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.mbsp_btn, name = resources.getString(R.string.mbsp))
                    }
            }*/
            sharedViewModel.saveCouncil()
            val name = sharedViewModel.getCouncilNameFromApp()
            val id = sharedViewModel.getCouncilIdFromApp()
            Log.d("MainActivity", "Penang fragment -> $name && $id")
            Snackbar.make(requireView(), "$name is selected.", Snackbar.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_penangCouncilFragment_to_councilFragment)
        }
    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        penangCouncilBinding = null
    }
}