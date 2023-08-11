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

    fun getUserKeepLoggedIn(): Boolean{
        return sharedPreferences.getBoolean(Constants.USER_KEEP_LOGGED_IN, true)
    }

    /** USER TOKEN **/
    fun isUserTokenSaved(): Boolean{
        val token = sharedPreferences.getInt(Constants.USER_TOKEN, 0)
        return token != 0
    }
    fun saveUserToken(id: Int){
        sharedPreferences.edit().putInt(Constants.USER_TOKEN, id).apply()
    }

    fun getUserToken(): Int{
        return sharedPreferences.getInt(Constants.USER_TOKEN, 0)
    }

    fun deleteUserToken(): Boolean{
        sharedPreferences.edit().remove(Constants.USER_TOKEN).apply()
        return isUserTokenSaved()
    }

    /** WALLET ID **/
    fun isWalletIDSaved(): Boolean{
        val token = sharedPreferences.getInt(Constants.USER_TOKEN, 0)
        return token != 0
    }
    fun saveWalletID(id: Int){
        sharedPreferences.edit().putInt(Constants.USER_TOKEN, id).apply()
    }

    fun getWalletID(): Int{
        return sharedPreferences.getInt(Constants.USER_TOKEN, 0)
    }

    fun deleteWalletID(): Boolean{
        sharedPreferences.edit().remove(Constants.USER_TOKEN).apply()
        return isUserTokenSaved()
    }


    /** COUNCIL #ID# APP PREF **/
    fun councilIdIsSelected(): Boolean {
        val token = sharedPreferences.getInt(Constants.COUNCIL_ID_SELECTED, 0)
        return token != 0
    }
    fun getCouncilSelectedId(): Int{
        return sharedPreferences.getInt(Constants.COUNCIL_ID_SELECTED, 0)
    }

    fun saveCouncilSelectedId(id: Int){
        sharedPreferences.edit().putInt(Constants.COUNCIL_ID_SELECTED, id).apply()
    }

    fun deleteCouncilSavedId(): Boolean {
        sharedPreferences.edit().remove(Constants.COUNCIL_ID_SELECTED).apply()
        return councilIdIsSelected()
    }
    /** COUNCIL #NAME# APP PREF **/
    fun councilNameIsSaved(): Boolean {
        val token = sharedPreferences.getString(Constants.COUNCIL_NAME_CHOSEN, null)
        return token != null
    }
    fun getCouncilName(): String{
        return sharedPreferences.getString(Constants.COUNCIL_NAME_CHOSEN, null).toString()
    }

    fun saveCouncilName(council: String){
        sharedPreferences.edit().putString(Constants.COUNCIL_NAME_CHOSEN, council).apply()
    }

    fun deleteCouncilSavedName(): Boolean {
        sharedPreferences.edit().remove(Constants.COUNCIL_NAME_CHOSEN).apply()
        return councilNameIsSaved()
    }
}