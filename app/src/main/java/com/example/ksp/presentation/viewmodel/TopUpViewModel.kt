package com.example.ksp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ksp.data.model.modelrequest.GetWalletRequest
import com.example.ksp.data.model.modelrequest.TopUpRequest
import com.example.ksp.data.util.Resource
import com.example.ksp.data.util.SharedPreference
import com.example.ksp.domain.usecase.WalletUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TopUpViewModel @Inject constructor(
    private val walletUseCase: WalletUseCase,
    private val sharedPreference: SharedPreference
) : ViewModel() {

    private val TAG = "MainActivity"
    val successful: MutableLiveData<Boolean?> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()

    private val _amount = MutableLiveData<Int>()
    val amount: LiveData<Int> = _amount
    private val _method = MutableLiveData<Int>()
    val method: LiveData<Int> = _method

    private var wallet_id: Int = 0

    fun setAmount(a: Int){
        _amount.value = a
    }
    fun setMethod(a: Int){
        _method.value = a
    }

    fun getAccountId(){
        val account_id = sharedPreference.getUserToken()
        if(account_id != 0){
            val walletRequest = GetWalletRequest(account_id)
            Log.d(TAG, "TopUpVM -> starting getWalletID api")
            walletUseCase.getWalletID(getWalletRequest = walletRequest).onEach { result ->
                when(result) {
                    is Resource.Loading -> {
                        Log.i(TAG, "TopUpVM -> I am Loading")
                    }
                    is Resource.Error -> {
                        error.postValue("${result.message}")
                        successful.postValue(false)
                        Log.i(TAG, "TopUpVM -> I am Error, ${result.message}")
                    }
                    is Resource.Success -> {
                        successful.postValue(true)
                        wallet_id = result.data?.wallet_id!!
                        Log.i(TAG, "TopUpVM -> I am Success, wallet id: ${result.data?.wallet_id}")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun topUp(){
        val topUpRequest = TopUpRequest(amount.value!!, wallet_id, method.value!!)
        walletUseCase.topUp(topUpRequest = topUpRequest).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    Log.i(TAG, "TopUpVM -> I am Loading")
                }
                is Resource.Error -> {
                    error.postValue("${result.message}")
                    successful.postValue(false)
                    Log.i(TAG, "TopUpVM -> I am Error, ${result.message}")
                }
                is Resource.Success -> {
                    successful.postValue(true)
                    Log.i(TAG, "TopUpVM -> I am Success, ${result.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun navigateToPage(){
        successful.postValue(null)
        error.postValue(null)
    }

}