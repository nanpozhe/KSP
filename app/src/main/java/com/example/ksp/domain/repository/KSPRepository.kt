package com.example.ksp.domain.repository

import com.example.ksp.data.model.modelrequest.LoginRequest
import com.example.ksp.data.model.modelrequest.NewPasswordRequest
import com.example.ksp.data.model.modelrequest.RegisterRequest
import com.example.ksp.data.model.modelrequest.VerifyRequest
import com.example.ksp.data.model.modelresponse.LoginResponse
import com.example.ksp.data.model.modelresponse.NewPasswordResponse
import com.example.ksp.data.model.modelresponse.RegisterResponse
import com.example.ksp.data.model.modelresponse.VerifyResponse
import com.example.ksp.data.util.Resource

interface KSPRepository {

    suspend fun performLogin(login : LoginRequest) : Resource<LoginResponse>
    suspend fun performRegister(register : RegisterRequest) : Resource<RegisterResponse>
    suspend fun performVerify(verify : VerifyRequest) : Resource<VerifyResponse>
    suspend fun updatePassword(password : NewPasswordRequest) :Resource<NewPasswordResponse>

}