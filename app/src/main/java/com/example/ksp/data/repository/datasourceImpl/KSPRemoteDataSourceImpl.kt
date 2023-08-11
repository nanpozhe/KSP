package com.example.ksp.data.repository.datasourceImpl

import com.example.ksp.data.api.KSPApiService
import com.example.ksp.data.model.modelrequest.*
import com.example.ksp.data.model.modelresponse.*
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

    override suspend fun getWalletID(getWallet: GetWalletRequest): Response<GetWalletResponse> {
        return kspApiService.getWalletID(getWallet = getWallet)
    }

    override suspend fun getWalletBalance(getBalance: GetBalanceRequest): Response<GetBalanceResponse> {
        return kspApiService.getWalletBalance(getBalance = getBalance)
    }

    override suspend fun topUp(topUp: TopUpRequest): Response<TopUpResponse> {
        return kspApiService.topUp(topUp = topUp)
    }
}