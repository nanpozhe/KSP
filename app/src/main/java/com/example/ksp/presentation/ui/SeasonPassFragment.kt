package com.example.ksp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ksp.R
import com.example.ksp.databinding.FragmentParkPayBinding
import com.example.ksp.databinding.FragmentSeasonPassBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeasonPassFragment : Fragment() {

    private var seasonPassBinding: FragmentSeasonPassBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSeasonPassBinding.inflate(inflater, container, false)
        seasonPassBinding = fragmentBinding
        return fragmentBinding.root
    }

}