package com.example.ksp.presentation.viewmodel

import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.ViewModel
import com.example.ksp.R
import com.example.ksp.data.util.SharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@HiltViewModel
class CouncilViewModel @Inject constructor(
    private val sharedPreference: SharedPreference
): ViewModel() {
        fun saveCouncil(id: Int, name: String){
            sharedPreference.saveCouncilSelectedId(id)
            sharedPreference.saveCouncilName(name)
        }
}