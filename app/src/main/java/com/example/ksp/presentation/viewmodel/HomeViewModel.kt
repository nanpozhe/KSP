package com.example.ksp.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ksp.data.model.modelrequest.GetBalanceRequest
import com.example.ksp.data.model.modelrequest.GetWalletRequest
import com.example.ksp.data.util.Resource
import com.example.ksp.data.util.SharedPreference
import com.example.ksp.domain.usecase.WalletUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app: Application,
    private val sharedPreference: SharedPreference,
    private val walletUseCase: WalletUseCase
): AndroidViewModel(app) {

    private val TAG = "MainActivity"
    val walletIdSuccessful: MutableLiveData<Boolean?> = MutableLiveData()
    val walletIdError: MutableLiveData<String?> = MutableLiveData()
    val balanceSuccessful: MutableLiveData<Boolean?> = MutableLiveData()
    val balanceError: MutableLiveData<String?> = MutableLiveData()
    var walletBalance: Float = 0.0F

    private val account_id = sharedPreference.getUserToken()
    private var wallet_id: Int = 0

    val username = sharedPreference.getUserName()

    fun getWalletId(){
        if(account_id != 0){
            val walletRequest = GetWalletRequest(account_id)
            Log.d(TAG, "HomeVM -> starting getWalletID api")
            walletUseCase.getWalletID(getWalletRequest = walletRequest).onEach { result ->
                when(result) {
                    is Resource.Loading -> {
                        Log.i(TAG, "HomeVM -> I am Loading")
                    }
                    is Resource.Error -> {
                        walletIdError.postValue("${result.message}")
                        walletIdSuccessful.postValue(false)
                        Log.i(TAG, "HomeVM -> I am Error, ${result.message}")
                    }
                    is Resource.Success -> {
                        walletIdSuccessful.postValue(true)
                        wallet_id = result.data?.wallet_id!!
                        sharedPreference.saveWalletID(wallet_id)
                        Log.i(TAG, "HomeVM -> I am Success, wallet id: ${result.data?.wallet_id}")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getWalletBalance(){
        val balanceRequest = GetBalanceRequest(wallet_id = wallet_id, account_id = account_id)
        Log.d(TAG, "HomeVM -> starting getWalletBalance api")
        walletUseCase.getWalletBalance(getBalanceRequest = balanceRequest).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    Log.i(TAG, "HomeVM -> I am Loading")
                }
                is Resource.Error -> {
                    balanceError.postValue("${result.message}")
                    balanceSuccessful.postValue(false)
                    Log.i(TAG, "HomeVM -> I am Error, ${result.message}")
                }
                is Resource.Success -> {
                    balanceSuccessful.postValue(true)
                    walletBalance = result.data?.amount!!
                    Log.i(TAG, "HomeVM -> I am Success, wallet balance: ${result.data?.amount}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun proceedToGetBalance(){
        walletIdSuccessful.postValue(null)
        walletIdError.postValue(null)
    }

    fun navigateToPage(){
        balanceSuccessful.postValue(null)
        balanceError.postValue(null)
    }
}