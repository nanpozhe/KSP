package com.example.ksp.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ksp.data.util.SharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val app: Application,
    private val sharedPreference: SharedPreference
) : ViewModel() {

    private val userToken = sharedPreference.getUserName()

    fun saveUserLoggedIn(){
        sharedPreference.saveUserAccessName(userToken)
    }

    fun unSaveUserLoggedIn(){
        val userIsLoggedIn = sharedPreference.deleteAccessName()
        Log.i("MainActivity", "SettingsVM -> $userIsLoggedIn")
    }

    fun unCheckUserKeepLoggedIn(){
        sharedPreference.saveUserKeepLoggedIn(choice = false)
    }

    fun checkUserKeepLoggedIn(){
        sharedPreference.saveUserKeepLoggedIn(choice = true)
    }
}