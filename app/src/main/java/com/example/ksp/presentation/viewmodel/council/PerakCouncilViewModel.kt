package com.example.ksp.presentation.viewmodel.council

import androidx.lifecycle.ViewModel
import com.example.ksp.data.util.SharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
class PerakCouncilViewModel @Inject constructor(
    private val sharedPreference: SharedPreference
): ViewModel() {
    fun saveCouncil(id: Int, name: String) {
        sharedPreference.saveCouncilSelectedId(id)
        sharedPreference.saveCouncilName(name)
    }

    fun getCouncilName(): String{
        return sharedPreference.getCouncilName()
    }

    fun getCouncilId(): Int{
        return sharedPreference.getCouncilSelectedId()
    }
}