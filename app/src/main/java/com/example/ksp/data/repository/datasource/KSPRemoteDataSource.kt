package com.example.ksp.data.repository.datasource

import com.example.ksp.data.model.modelrequest.*
import com.example.ksp.data.model.modelresponse.*
import retrofit2.Response
import retrofit2.http.Body

interface KSPRemoteDataSource {

    suspend fun performLogin(login : LoginRequest): Response<LoginResponse>
    suspend fun performRegister(register : RegisterRequest): Response<RegisterResponse>
    suspend fun performVerify(verify : VerifyRequest) : Response<VerifyResponse>
    suspend fun updatePassword(password : NewPasswordRequest) : Response<NewPasswordResponse>
    suspend fun getWalletID(getWallet : GetWalletRequest) : Response<GetWalletResponse>
    suspend fun getWalletBalance(getBalance: GetBalanceRequest) : Response<GetBalanceResponse>
    suspend fun topUp(topUp : TopUpRequest) : Response<TopUpResponse>
    suspend fun createCar(createCar: CreateCarRequest) : Response<CarResponse>
    suspend fun editCar(editCar: EditCarRequest) : Response<CarResponse>
    suspend fun deleteCar(deleteCar: DeleteCarRequest) : Response<CarResponse>
    suspend fun getAllCar(getAllCar: GetAllCarRequest) : Response<GetAllCarResponse>
}