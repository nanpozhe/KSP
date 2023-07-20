package com.example.ksp.data.api

import com.example.ksp.data.model.modelrequest.LoginRequest
import com.example.ksp.data.model.modelrequest.NewPasswordRequest
import com.example.ksp.data.model.modelrequest.RegisterRequest
import com.example.ksp.data.model.modelrequest.VerifyRequest
import com.example.ksp.data.model.modelresponse.LoginResponse
import com.example.ksp.data.model.modelresponse.NewPasswordResponse
import com.example.ksp.data.model.modelresponse.RegisterResponse
import com.example.ksp.data.model.modelresponse.VerifyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
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
}