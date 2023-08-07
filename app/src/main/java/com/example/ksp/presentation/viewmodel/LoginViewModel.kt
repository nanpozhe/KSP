package com.example.ksp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ksp.data.model.modelrequest.LoginRequest
import com.example.ksp.data.util.Resource
import com.example.ksp.data.util.SharedPreference
import com.example.ksp.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val authUseCase: AuthUseCase,
  private val sharedPrefUtil: SharedPreference
) : ViewModel() {

  private val TAG = "MainActivtity"

  val successful: MutableLiveData<Boolean?> = MutableLiveData()
  val error: MutableLiveData<String?> = MutableLiveData()

  val loggedIn : String = sharedPrefUtil.getUserName()

  private fun saveUserAccessName(name: String) = sharedPrefUtil.saveUserAccessName(name)

  fun login(loginRequest: LoginRequest){
    authUseCase.performLogin(loginRequest = loginRequest).onEach { result ->
      when (result) {
        is Resource.Loading -> {
          Log.i(TAG, "LoginVM -> I am Loading")
        }
        is Resource.Error -> {
          error.postValue("${result.message}")
          successful.postValue(false)
          Log.i(TAG, "LoginVM -> I am Error, ${result.message}")
        }
        is Resource.Success -> {
          successful.postValue(true)
          saveUserAccessName("${result.data?.fullname}")
          sharedPrefUtil.saveUserToken("${result.data?.token}".toInt())
          Log.i(TAG, "LoginVM -> I am Success, ${result.data?.fullname}")
        }
      }
    }.launchIn(viewModelScope)
  }


  fun navigateToPage(){
    successful.postValue(null)
    error.postValue(null)
  }
}