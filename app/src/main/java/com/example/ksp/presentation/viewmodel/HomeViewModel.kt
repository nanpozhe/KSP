package com.example.ksp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.ksp.data.util.SharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app: Application,
    private val sharedPreference: SharedPreference
): AndroidViewModel(app) {

    val username = sharedPreference.getUserName()
}