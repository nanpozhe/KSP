package com.example.ksp.presentation.ui.council

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.ksp.R
import com.example.ksp.presentation.viewmodel.council.PenangCouncilViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PenangCouncilFragment : Fragment() {

    companion object {
        fun newInstance() = PenangCouncilFragment()
    }

    private lateinit var viewModel: PenangCouncilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_penang_council, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PenangCouncilViewModel::class.java)
        // TODO: Use the ViewModel
    }

    // TODO: modify this to check which council to save
    fun onRadioButtonClicked(view: View){
        if(view is RadioButton){
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id. ->
                    if (checked) {
                        // Pirates are the best
                    }
                R.id. ->
                    if (checked) {
                        // Ninjas rule
                    }
            }
        }
    }

}