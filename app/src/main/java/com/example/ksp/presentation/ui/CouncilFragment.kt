package com.example.ksp.presentation.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.ksp.R
import com.example.ksp.databinding.FragmentCouncilBinding
import com.example.ksp.presentation.viewmodel.CouncilViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CouncilFragment : Fragment() {

    companion object {
        fun newInstance() = CouncilFragment()
    }

    val viewModel: CouncilViewModel by viewModels()
    private lateinit var councilBinding: FragmentCouncilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentCouncilBinding.inflate(inflater, container, false)
        councilBinding = fragmentBinding
        return fragmentBinding.root
    }


}