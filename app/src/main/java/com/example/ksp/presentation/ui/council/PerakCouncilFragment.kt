package com.example.ksp.presentation.ui.council

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ksp.R
import com.example.ksp.presentation.viewmodel.council.PerakCouncilViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PerakCouncilFragment : Fragment() {

    companion object {
        fun newInstance() = PerakCouncilFragment()
    }

    private lateinit var viewModel: PerakCouncilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perak_council, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PerakCouncilViewModel::class.java)
        // TODO: Use the ViewModel
    }

}