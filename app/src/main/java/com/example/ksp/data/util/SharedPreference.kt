package com.example.ksp.data.util

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreference @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun isFirstAppLaunch(): Boolean {
        return sharedPreferences.getBoolean(Constants.IS_FIRST_APP_LAUNCH, true)
    }

    fun saveFirstAppLaunch(value: Boolean) {
        return sharedPreferences.edit().putBoolean(Constants.IS_FIRST_APP_LAUNCH, value).apply()
    }

    fun userIsLoggedIn(): Boolean{
        val token = sharedPreferences.getString(Constants.USER_IS_LOGGED_IN, null)
        return token != null
    }

    fun getUserName(): String {
        return sharedPreferences.getString(Constants.USER_IS_LOGGED_IN, "").toString()
    }

    fun saveUserAccessName(name: String) {
        sharedPreferences.edit().putString(Constants.USER_IS_LOGGED_IN, name).apply()
    }

    fun deleteAccessName(): Boolean {
        sharedPreferences.edit().remove(Constants.USER_IS_LOGGED_IN).apply()
        return userIsLoggedIn()
    }

    fun userKeepLoggedIn(): Boolean{
        val token = sharedPreferences.getString(Constants.USER_KEEP_LOGGED_IN, null)
        return token != null
    }

    fun saveUserKeepLoggedIn(choice: Boolean){
        sharedPreferences.edit().putBoolean(Constants.USER_KEEP_LOGGED_IN, choice).apply()
    }
}