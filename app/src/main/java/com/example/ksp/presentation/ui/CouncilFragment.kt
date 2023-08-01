package com.example.ksp.presentation.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ksp.R
import com.example.ksp.databinding.FragmentCouncilBinding
import com.example.ksp.presentation.viewmodel.CouncilViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CouncilFragment : Fragment() {

    companion object {
        fun newInstance() = CouncilFragment()
    }

    val viewModel: CouncilViewModel by viewModels()
    private lateinit var councilBinding: FragmentCouncilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentCouncilBinding.inflate(inflater, container, false)
        councilBinding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

/*    fun onRadioButtonClicked(view: View){
        if(view is RadioButton){
            // Is the button now checked?
            val checked = view.isChecked
            val found = false
            val i = 0
            // Check which button is checked and retrieve the value from the matched key
            var result = councils.filterKeys { it == view.id }
            Log.i("MainActivity", "CouncilFragment -> view:${view.text}&${view.id}  ||  key:${councils}")
            Snackbar.make(view, "${view.text} is selected", Snackbar.LENGTH_LONG).show()
        }
    }*/
}