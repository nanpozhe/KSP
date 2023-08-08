package com.example.ksp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ksp.data.util.SharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopUpViewModel @Inject constructor(
    private val sharedPreference: SharedPreference
) : ViewModel() {

    private val _amount = MutableLiveData<Int>()
    val amount: LiveData<Int> = _amount

    private val _method = MutableLiveData<Int>()
    val method: LiveData<Int> = _method

    //private val wallet_id =

}