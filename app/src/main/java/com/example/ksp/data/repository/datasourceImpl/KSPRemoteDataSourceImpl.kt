package com.example.ksp.data.repository.datasourceImpl

import com.example.ksp.data.api.KSPApiService
import com.example.ksp.data.model.modelrequest.LoginRequest
import com.example.ksp.data.model.modelrequest.NewPasswordRequest
import com.example.ksp.data.model.modelrequest.RegisterRequest
import com.example.ksp.data.model.modelrequest.VerifyRequest
import com.example.ksp.data.model.modelresponse.LoginResponse
import com.example.ksp.data.model.modelresponse.NewPasswordResponse
import com.example.ksp.data.model.modelresponse.RegisterResponse
import com.example.ksp.data.model.modelresponse.VerifyResponse
import com.example.ksp.data.repository.datasource.KSPRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class KSPRemoteDataSourceImpl @Inject constructor(
    private val kspApiService: KSPApiService
) : KSPRemoteDataSource {

    override suspend fun performLogin(login: LoginRequest): Response<LoginResponse> {
        return kspApiService.performLogin(login = login)
    }

    override suspend fun performRegister(register: RegisterRequest): Response<RegisterResponse> {
        return kspApiService.performRegister(register = register)
    }

    override suspend fun performVerify(verify: VerifyRequest): Response<VerifyResponse> {
        return kspApiService.performVerify(verify = verify)
    }

    override suspend fun updatePassword(password: NewPasswordRequest): Response<NewPasswordResponse> {
        return kspApiService.updatePassword(newPassword = password)
    }
}