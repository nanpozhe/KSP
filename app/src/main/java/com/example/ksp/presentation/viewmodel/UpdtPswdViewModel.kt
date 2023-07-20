package com.example.ksp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ksp.data.model.modelrequest.NewPasswordRequest
import com.example.ksp.data.util.Resource
import com.example.ksp.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

//@HiltViewModel
class UpdtPswdViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
): ViewModel() {

    val successful: MutableLiveData<Boolean?> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()

    fun updatePassword(newPasswordRequest: NewPasswordRequest){
        authUseCase.updatePassword(passwordRequest = newPasswordRequest).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    Log.i("UpdatePasswordViewModel", "I am here, Loading")
                }
                is Resource.Error -> {
                    error.postValue("${result.message}")
                    successful.postValue(false)
                    Log.i("UpdatePasswordViewModel", "I am here, Error ${result.message}")
                }
                is Resource.Success -> {
                    successful.postValue(true)
                    Log.i("UpdatePasswordViewModel", "I am here, Success ${result.data?.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun navigateToPage(){
        successful.postValue(null)
        error.postValue(null)
    }
}