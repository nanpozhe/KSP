package com.example.ksp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ksp.data.model.modelrequest.CreateCarRequest
import com.example.ksp.data.model.modelrequest.DeleteCarRequest
import com.example.ksp.data.model.modelrequest.GetAllCarRequest
import com.example.ksp.data.util.Resource
import com.example.ksp.data.util.SharedPreference
import com.example.ksp.domain.usecase.CarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val carUseCase: CarUseCase,
    private val sharedPreference: SharedPreference
): ViewModel() {
    private val TAG = "MainActivity"
    val successful: MutableLiveData<Boolean?> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()
    val successfulCarInfo: MutableLiveData<Boolean?> = MutableLiveData()
    val errorCarInfo: MutableLiveData<String?> = MutableLiveData()

    private val _carColor = MutableLiveData<String>()
    private val _carBrand = MutableLiveData<String>()
    private val accountID = sharedPreference.getUserToken()

    private val _carInfo: MutableMap<String, Int> = mutableMapOf()
    val carInfo: Map<String, Int> = _carInfo
    private val _keys: MutableList<String> = mutableListOf()
    val keys: List<String> = _keys

    fun setCarColor(c: String){
        _carColor.value = c
    }
    fun setCarBrand(c: String){
        _carBrand.value = c
    }

    // use in dialogCarFragment
    fun createCar(cp: String, cc: String, cb: String){
        val createCarRequest = CreateCarRequest(cp, cb, cc, accountID)
        Log.d(TAG, "CarVM -> createCarRequest : $createCarRequest")
        carUseCase.createCar(createCarRequest = createCarRequest).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    Log.i(TAG, "CarVM -> I am Loading")
                }
                is Resource.Error -> {
                    error.postValue("${result.message}")
                    successful.postValue(false)
                    Log.i(TAG, "CarVM -> I am Error, ${result.message}")
                }
                is Resource.Success -> {
                    successful.postValue(true)
                    Log.i(TAG, "CarVM -> I am Success, ${result.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteCar(car_plate_delete: String){
        val deleteCarRequest = DeleteCarRequest(car_plate_delete, accountID)
        Log.d(TAG, "CarVM -> deleteCarRequest : $deleteCarRequest")
        carUseCase.deleteCar(deleteCarRequest = deleteCarRequest).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    Log.i(TAG, "CarVM -> I am Loading")
                }
                is Resource.Error -> {
                    error.postValue("${result.message}")
                    successful.postValue(false)
                    Log.i(TAG, "CarVM -> I am Error, ${result.message}")
                }
                is Resource.Success -> {
                    successful.postValue(true)
                    Log.i(TAG, "CarVM -> I am Success, ${result.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getCarInfo(){
        val getAllCarRequest = GetAllCarRequest(account_id = accountID)
        Log.d(TAG, "CarVM -> getting all car request")
        carUseCase.getAllCarInfo(getAllCarRequest = getAllCarRequest).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    Log.i(TAG, "CarVM -> I am Loading")
                }
                is Resource.Error -> {
                    errorCarInfo.postValue("${result.message}")
                    successfulCarInfo.postValue(false)
                    Log.i(TAG, "CarVM -> I am Error, ${result.message}")
                }
                is Resource.Success -> {
                    successfulCarInfo.postValue(true)
                    Log.i(TAG, "CarVM -> I am Success, ${result.message}")
                    _carInfo[result.data?.car_plate_1!!] = result.data.car_id_1
                    _carInfo[result.data?.car_plate_2!!] = result.data.car_id_2
                    _carInfo[result.data?.car_plate_3!!] = result.data.car_id_3
                    _carInfo[result.data?.car_plate_4!!] = result.data.car_id_4
                    _carInfo[result.data?.car_plate_5!!] = result.data.car_id_5
                    _keys.clear()
                    _keys.add(result.data?.car_plate_1)
                    _keys.add(result.data?.car_plate_2)
                    _keys.add(result.data?.car_plate_3)
                    _keys.add(result.data?.car_plate_4)
                    _keys.add(result.data?.car_plate_5)
                    Log.d(TAG, "CarVM -> carInfo: ${carInfo.keys} + ${carInfo.values} |~~~| keys: ${keys}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun saveCarPlate(cp: String){
        sharedPreference.saveCarPlate(car_plate = cp)
        val id = _carInfo[cp]
        sharedPreference.saveCarId(id!!)
    }

    fun getCarPlate(): String{
        return sharedPreference.getCarPlate()
    }

    fun resetIndicator(){
        successful.postValue(null)
        error.postValue(null)
    }

}
/*
way to add value into MutableLiveData<List<String>>
//private val _keys = MutableLiveData<List<String>>(listOf())
//val keys: LiveData<List<String>> = _keys
_keys.value = (_keys.value ?: emptyList()) + result.data?.car_plate_1!!
_keys.value = (_keys.value ?: emptyList()) + result.data?.car_plate_2!!
_keys.value = (_keys.value ?: emptyList()) + result.data?.car_plate_3!!
_keys.value = (_keys.value ?: emptyList()) + result.data?.car_plate_4!!
_keys.value = (_keys.value ?: emptyList()) + result.data?.car_plate_5!!
*/