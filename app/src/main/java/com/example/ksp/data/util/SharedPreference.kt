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

    /** USERNAME **/
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

    /** KEEP LOGGED IN token **/
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

    /** USER TOKEN or ACCOUNT ID **/
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
        val token = sharedPreferences.getInt(Constants.WALLET_ID, 0)
        return token != 0
    }
    fun saveWalletID(id: Int){
        sharedPreferences.edit().putInt(Constants.WALLET_ID, id).apply()
    }

    fun getWalletID(): Int{
        return sharedPreferences.getInt(Constants.WALLET_ID, 0)
    }

    fun deleteWalletID(): Boolean{
        sharedPreferences.edit().remove(Constants.WALLET_ID).apply()
        return isWalletIDSaved()
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
    /** CAR #PLATE# APP PREF **/
    fun carPlateIsSaved(): Boolean {
        val token = sharedPreferences.getString(Constants.CAR_PLATE, null)
        return token != null
    }
    fun getCarPlate(): String{
        return sharedPreferences.getString(Constants.CAR_PLATE, null).toString()
    }

    fun saveCarPlate(car_plate: String){
        sharedPreferences.edit().putString(Constants.CAR_PLATE, car_plate).apply()
    }

    fun deleteCarPlate(): Boolean {
        sharedPreferences.edit().remove(Constants.CAR_PLATE).apply()
        return carPlateIsSaved()
    }

    /** CAR #ID# APP PREF **/
    fun carIDIsSaved(): Boolean {
        val token = sharedPreferences.getInt(Constants.CAR_ID, 0)
        return token != null
    }
    fun getCarId(): Int{
        return sharedPreferences.getInt(Constants.CAR_ID, 0)
    }

    fun saveCarId(car_plate: Int){
        sharedPreferences.edit().putInt(Constants.CAR_ID, car_plate).apply()
    }

    fun deleteCarId(): Boolean {
        sharedPreferences.edit().remove(Constants.CAR_ID).apply()
        return carIDIsSaved()
    }

    /** PARKING STATUS APP PREF **/
    fun parkingStatusIsSaved(): Boolean {
        val token = sharedPreferences.getString(Constants.PARKING_STATUS, null)
        return token != null
    }
    fun getParkingStatus(): String{
        return sharedPreferences.getString(Constants.PARKING_STATUS, "Not Parking").toString()
    }
    fun saveParkingStatus(status: String){
        sharedPreferences.edit().putString(Constants.PARKING_STATUS, status).apply()
    }
    fun deleteParkingStatus(): Boolean {
        sharedPreferences.edit().remove(Constants.PARKING_STATUS).apply()
        return parkingStatusIsSaved()
    }

    /** PARKING DURATION APP PREF **/
    fun parkingDurationIsSaved(): Boolean {
        val token = sharedPreferences.getLong(Constants.PARKING_DURATION, 0)
        return token != null
    }
    fun getParkingDuration(): Int{
        return sharedPreferences.getInt(Constants.PARKING_DURATION, 0)
    }
    fun saveParkingDuration(duration: Int){
        sharedPreferences.edit().putInt(Constants.PARKING_DURATION, duration).apply()
    }
    fun deleteParkingDuration(): Boolean {
        sharedPreferences.edit().remove(Constants.PARKING_DURATION).apply()
        return parkingDurationIsSaved()
    }
}