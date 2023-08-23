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
import com.example.ksp.databinding.FragmentPerakCouncilBinding
import com.example.ksp.presentation.viewmodel.CouncilViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PerakCouncilFragment : Fragment() {

    companion object {
        fun newInstance() = PerakCouncilFragment()
    }

    private val sharedViewModel: CouncilViewModel by activityViewModels()
    private var perakCouncilBinding: FragmentPerakCouncilBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPerakCouncilBinding.inflate(inflater, container, false)
        perakCouncilBinding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        perakCouncilBinding?.apply {
            lifecycleOwner = viewLifecycleOwner                 // specify the fragment as the lifecycle owner
            viewModel = sharedViewModel
            perakCouncilFragment = this@PerakCouncilFragment
        }
    }

    fun onRadioButtonClicked(){
        /*if(view is RadioButton){
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.ipoh_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.ipoh_btn, name = resources.getString(R.string.ipoh))
                    }
                R.id.kampar_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.kampar_btn, name = resources.getString(R.string.kampar))
                    }
                R.id.taiping_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.taiping_btn, name = resources.getString(R.string.taiping))
                    }
                R.id.manjung_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.manjung_btn, name = resources.getString(R.string.manjung))
                    }
                R.id.teluk_intan_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.teluk_intan_btn, name = resources.getString(R.string.telukIntan))
                    }
                R.id.batu_gajah_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.batu_gajah_btn, name = resources.getString(R.string.batuGajah))
                    }
                R.id.gerik_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.gerik_btn, name = resources.getString(R.string.gerik))
                    }
                R.id.kuala_kangsar_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.kuala_kangsar_btn, name = resources.getString(R.string.kualaKangsar))
                    }
                R.id.kerian_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.kerian_btn, name = resources.getString(R.string.kerian))
                    }
                R.id.perak_tengah_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.perak_tengah_btn, name = resources.getString(R.string.perakTengah))
                    }
                R.id.tanjong_malim_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.tanjong_malim_btn, name = resources.getString(R.string.tanjongMalim))
                    }
                R.id.tapah_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.tapah_btn, name = resources.getString(R.string.tapah))
                    }
                R.id.selama_btn ->
                    if (checked) {
                        sharedViewModel.setCouncil(id = R.id.selama_btn, name = resources.getString(R.string.selama))
                    }
            }*/
            sharedViewModel.saveCouncil()
            val name = sharedViewModel.getCouncilNameFromApp()
            val id = sharedViewModel.getCouncilIdFromApp()
            Log.d("MainActivity", "Perak fragment -> $name && $id")
            Snackbar.make(requireView(), "$name is selected.", Snackbar.LENGTH_LONG).show()
            toHomeFragment()
    }

    fun toHomeFragment(){
        findNavController().navigate(R.id.action_perakCouncilFragment2_to_homeFragment)
    }
    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        perakCouncilBinding = null
    }
}
