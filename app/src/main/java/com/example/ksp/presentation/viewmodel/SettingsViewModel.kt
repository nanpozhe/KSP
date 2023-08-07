package com.example.ksp.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ksp.data.util.SharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val app: Application,
    private val sharedPreference: SharedPreference
) : ViewModel() {

    private val _userKeepLoggedIn = MutableLiveData<Boolean>()
    val userKeepLoggedIn: LiveData<Boolean> = _userKeepLoggedIn

    private val userToken = sharedPreference.getUserName()

    fun initCheckUserLoggedInToggle() {
        _userKeepLoggedIn.value = sharedPreference.getUserKeepLoggedIn()
    }

    fun unSaveUserLoggedIn(){
        val userIsLoggedIn = sharedPreference.deleteAccessName()
        val userTokenGone = sharedPreference.deleteUserToken()
        Log.i("MainActivity", "SettingsVM -> $userIsLoggedIn + $userTokenGone")
    }

    fun changeUserKeepLoggedIn(choice: Boolean) {
        sharedPreference.saveUserKeepLoggedIn(choice)
        _userKeepLoggedIn.value = choice
    }
}