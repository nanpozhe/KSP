package com.example.ksp.presentation.ui.council

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ksp.R
import com.example.ksp.presentation.viewmodel.council.SelangorCouncilViewModel

class SelangorCouncilFragment : Fragment() {

    companion object {
        fun newInstance() = SelangorCouncilFragment()
    }

    private lateinit var viewModel: SelangorCouncilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selangor_council, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SelangorCouncilViewModel::class.java)
        // TODO: Use the ViewModel
    }

}