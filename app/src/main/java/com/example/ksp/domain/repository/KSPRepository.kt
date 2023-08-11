package com.example.ksp.domain.repository

import com.example.ksp.data.model.modelrequest.*
import com.example.ksp.data.model.modelresponse.*
import com.example.ksp.data.util.Resource

interface KSPRepository {

    suspend fun performLogin(login : LoginRequest) : Resource<LoginResponse>
    suspend fun performRegister(register : RegisterRequest) : Resource<RegisterResponse>
    suspend fun performVerify(verify : VerifyRequest) : Resource<VerifyResponse>
    suspend fun updatePassword(password : NewPasswordRequest) :Resource<NewPasswordResponse>
    suspend fun getWalletID(getWallet: GetWalletRequest) : Resource<GetWalletResponse>
    suspend fun getWalletBalance(getBalance: GetBalanceRequest) : Resource<GetBalanceResponse>
    suspend fun topUp(topUp: TopUpRequest) : Resource<TopUpResponse>

}