package com.example.ksp.domain.usecase

import android.util.Log
import com.example.ksp.data.model.modelrequest.GetWalletRequest
import com.example.ksp.data.model.modelrequest.TopUpRequest
import com.example.ksp.data.model.modelresponse.GetWalletResponse
import com.example.ksp.data.model.modelresponse.TopUpResponse
import com.example.ksp.data.util.Resource
import com.example.ksp.domain.repository.KSPRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WalletUseCase @Inject constructor(
    private val kspRepository: KSPRepository
) {
    private val TAG = "MainActivtity"

    fun getWalletID(getWalletRequest: GetWalletRequest) : Flow<Resource<GetWalletResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = kspRepository.getWalletID(getWallet = getWalletRequest)
            Log.i(TAG, "WalletUseCase -> I am retrieving wallet id, ${response.data?.message}")
            emit(response)
        } catch (e: HttpException){
            Log.i(TAG, "WalletUseCase, getWalletId -> ${e.localizedMessage}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException){
            Log.i(TAG, "WalletUseCase, getWalletID -> ${e.localizedMessage}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

    fun topUp(topUpRequest: TopUpRequest) : Flow<Resource<TopUpResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = kspRepository.topUp(topUp = topUpRequest)
            Log.i(TAG, "WalletUseCase -> I am top up, ${response.data?.message}")
            emit(response)
        } catch (e: HttpException){
            Log.i(TAG, "WalletUseCase, topping up, ${e.localizedMessage}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected message"))
        } catch (e: IOException){
            Log.i(TAG, "WalletUseCase, topping up, ${e.localizedMessage}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}