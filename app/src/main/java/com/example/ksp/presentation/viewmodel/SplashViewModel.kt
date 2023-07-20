package com.example.ksp.presentation.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.ksp.data.util.SharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sharedPreferences: SharedPreference
): ViewModel() {
    val loggedIn: Boolean = sharedPreferences.userIsLoggedIn()
}