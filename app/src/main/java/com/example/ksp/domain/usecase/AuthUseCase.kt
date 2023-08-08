package com.example.ksp.domain.usecase

import android.util.Log
import com.example.ksp.data.model.modelrequest.LoginRequest
import com.example.ksp.data.model.modelrequest.NewPasswordRequest
import com.example.ksp.data.model.modelrequest.RegisterRequest
import com.example.ksp.data.model.modelrequest.VerifyRequest
import com.example.ksp.data.model.modelresponse.LoginResponse
import com.example.ksp.data.model.modelresponse.NewPasswordResponse
import com.example.ksp.data.model.modelresponse.RegisterResponse
import com.example.ksp.data.model.modelresponse.VerifyResponse
import com.example.ksp.data.util.Resource
import com.example.ksp.domain.repository.KSPRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val kspRepository: KSPRepository
) {
    private val TAG = "MainActivtity"

    fun performLogin(loginRequest: LoginRequest) : Flow<Resource<LoginResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = kspRepository.performLogin(login = loginRequest)
            Log.i(TAG, "AuthUseCase -> I am login, ${response.data?.message}")
            emit(response)
        } catch (e: HttpException) {
            Log.i(TAG, "AuthUseCase, login -> ${e.localizedMessage!!}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.i(TAG, "AuthUseCase, login -> ${e.localizedMessage!!}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    fun performRegister(registerRequest: RegisterRequest) : Flow<Resource<RegisterResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = kspRepository.performRegister(register = registerRequest)
            Log.i(TAG, "AuthUseCase -> I am register, ${response.data?.message}")
            emit(response)
        } catch (e: HttpException) {
            Log.i(TAG, "AuthUseCase, registering -> ${e.localizedMessage}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected message"))
        } catch (e: IOException) {
            Log.i(TAG, "AuthUseCase, registering -> ${e.localizedMessage}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

    fun performVerify(verifyRequest: VerifyRequest) : Flow<Resource<VerifyResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = kspRepository.performVerify(verify = verifyRequest)
            Log.i(TAG, "AuthUseCase -> I am verifying, ${response.data?.message}")
            emit(response)
        } catch (e: HttpException) {
            Log.i(TAG, "AuthUseCase, verifying -> ${e.localizedMessage}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected message"))
        } catch (e: IOException){
            Log.i(TAG, "AuthUseCase, verifying -> ${e.localizedMessage}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }

    fun updatePassword(passwordRequest: NewPasswordRequest) : Flow<Resource<NewPasswordResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = kspRepository.updatePassword(password = passwordRequest)
            Log.i(TAG, "AuthUseCase -> I am updating password, ${response.data?.message}")
            emit(response)
        } catch (e: HttpException) {
            Log.i(TAG, "AuthUseCase, updating password -> ${e.localizedMessage}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected message"))
        } catch (e: IOException){
            Log.i(TAG, "AuthUseCase, updating password -> ${e.localizedMessage}")
            emit(Resource.Error("Couldn't rearch server. Check your internet connection"))
        }
    }
}