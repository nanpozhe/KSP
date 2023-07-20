package com.example.ksp.data.repository.datasource

import com.example.ksp.data.model.modelrequest.LoginRequest
import com.example.ksp.data.model.modelrequest.NewPasswordRequest
import com.example.ksp.data.model.modelrequest.RegisterRequest
import com.example.ksp.data.model.modelrequest.VerifyRequest
import com.example.ksp.data.model.modelresponse.LoginResponse
import com.example.ksp.data.model.modelresponse.NewPasswordResponse
import com.example.ksp.data.model.modelresponse.RegisterResponse
import com.example.ksp.data.model.modelresponse.VerifyResponse
import retrofit2.Response

interface KSPRemoteDataSource {

    suspend fun performLogin(login : LoginRequest): Response<LoginResponse>
    suspend fun performRegister(register : RegisterRequest): Response<RegisterResponse>
    suspend fun performVerify(verify : VerifyRequest) : Response<VerifyResponse>
    suspend fun updatePassword(password : NewPasswordRequest) : Response<NewPasswordResponse>


}