package com.example.ksp.data.api

import com.example.ksp.data.model.modelrequest.*
import com.example.ksp.data.model.modelresponse.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface KSPApiService {

    @POST("/login.php")
    suspend fun performLogin(@Body login : LoginRequest) : Response<LoginResponse>

    @POST("/register.php")
    suspend fun performRegister(@Body register : RegisterRequest) : Response<RegisterResponse>

    @POST("/verify.php")
    suspend fun performVerify(@Body verify: VerifyRequest) : Response<VerifyResponse>

    @POST("/updatePswd.php")
    suspend fun updatePassword(@Body newPassword: NewPasswordRequest) : Response<NewPasswordResponse>

    @POST("/getWallet.php")
    suspend fun getWalletID(@Body getWallet: GetWalletRequest) : Response<GetWalletResponse>

    @POST("/getBalance.php")
    suspend fun getWalletBalance(@Body getBalance: GetBalanceRequest) : Response<GetBalanceResponse>

    @POST("/topup.php")
    suspend fun topUp(@Body topUp: TopUpRequest) : Response<TopUpResponse>
}