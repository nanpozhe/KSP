package com.example.ksp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ksp.data.model.ValidationResult
import com.example.ksp.data.model.modelrequest.GetBalanceRequest
import com.example.ksp.data.model.modelrequest.GetWalletRequest
import com.example.ksp.data.model.modelrequest.TopUpRequest
import com.example.ksp.data.util.Resource
import com.example.ksp.data.util.SharedPreference
import com.example.ksp.data.util.Utils.validateTopUpRequest
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

    private val wallet_id: Int = sharedPreference.getWalletID()
    private val account_id: Int = sharedPreference.getUserToken()
    var balance: Float = 0.0F

    fun setAmount(a: Int){
        _amount.value = a
    }
    fun setMethod(a: Int){
        _method.value = a
    }

    fun topUp(){
        val topUpRequest = TopUpRequest(amount.value!!, wallet_id, method.value!!)
        Log.d(TAG, "TopUpMethodVM -> topUpRequest obj : $topUpRequest")
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

    fun getWalletBalance(){
        val balanceRequest = GetBalanceRequest(wallet_id = wallet_id, account_id = account_id)
        Log.d(TAG, "TopUpVM -> starting getWalletBalance api")
        walletUseCase.getWalletBalance(getBalanceRequest = balanceRequest).onEach { result ->
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
                    balance = result.data?.amount!!
                    Log.i(TAG, "TopUpVM -> I am Success, wallet balance: ${result.data?.amount}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun validateVariable(): ValidationResult{
        return validateTopUpRequest(amount = amount.value!!, method = method.value!!)
    }

    fun navigateToPage(){
        successful.postValue(null)
        error.postValue(null)
    }

}