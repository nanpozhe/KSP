package com.example.ksp.domain.usecase

import android.util.Log
import com.example.ksp.data.model.modelrequest.ParkNPayRequest
import com.example.ksp.data.model.modelresponse.ParkNPayResponse
import com.example.ksp.data.util.Resource
import com.example.ksp.domain.repository.KSPRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ParkNPayUseCase @Inject constructor(
    private val kspRepository: KSPRepository
){
    private val TAG = "MainActivity"
    private val PPUseCase = "ParkNPayUseCase"

    fun parkNPay(parkNPayRequest: ParkNPayRequest) : Flow<Resource<ParkNPayResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = kspRepository.parkNPay(parkNPay = parkNPayRequest)
            Log.i(TAG, "$PPUseCase -> I am paying, ${response.data?.message}")
            emit(response)
        } catch (e: HttpException) {
            Log.i(TAG, "$PPUseCase, parking -> ${e.localizedMessage!!}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.i(TAG, "$PPUseCase, parking -> ${e.localizedMessage!!}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}