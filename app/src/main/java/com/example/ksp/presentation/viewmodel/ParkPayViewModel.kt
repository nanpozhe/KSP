package com.example.ksp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ksp.data.model.modelrequest.ParkNPayRequest
import com.example.ksp.data.util.Resource
import com.example.ksp.data.util.SharedPreference
import com.example.ksp.domain.usecase.ParkNPayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ParkPayViewModel @Inject constructor(
    private val ppUseCase: ParkNPayUseCase,
    private val sharedPreference: SharedPreference
) : ViewModel() {

    private val TAG = "MainActivity"

    val successful: MutableLiveData<Boolean?> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()

    private val walletID = sharedPreference.getWalletID()
    private val _duration = MutableLiveData<Int>()
    val duration: LiveData<Int> = _duration
    private val _amount = MutableLiveData<Double>()
    val amount: LiveData<Double> = _amount

    fun setDurationAmount(d: Int, a: Double){
        _duration.value = d
        _amount.value = a
    }

    fun parkNPay(){
        val pprequest = ParkNPayRequest(
            pp_amount = amount.value!!,
            pp_duration = duration.value!!,
            wallet_id = walletID,
            car_id = sharedPreference.getCarId(),
            council_name = getCouncilSelectedName())
        Log.d(TAG, "PPVM  -> parking : $pprequest")
        ppUseCase.parkNPay(parkNPayRequest = pprequest).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    Log.i(TAG, "PPVM -> I am Loading")
                }
                is Resource.Error -> {
                    error.postValue("${result.message}")
                    successful.postValue(false)
                    Log.i(TAG, "PPVM -> I am Error, ${result.message}")
                }
                is Resource.Success -> {
                    successful.postValue(true)
                    sharedPreference.saveParkingStatus("Parking")
                    Log.i(TAG, "PPVM -> I am Success, ${result.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getCouncilSelectedName(): String{
        return sharedPreference.getCouncilName()
    }

    fun getCarPlateSelected(): String{
        return sharedPreference.getCarPlate()
    }

}