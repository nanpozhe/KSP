package com.example.ksp.data.repository

import android.util.Log
import com.example.ksp.data.model.modelrequest.LoginRequest
import com.example.ksp.data.model.modelrequest.NewPasswordRequest
import com.example.ksp.data.model.modelrequest.RegisterRequest
import com.example.ksp.data.model.modelrequest.VerifyRequest
import com.example.ksp.data.model.modelresponse.LoginResponse
import com.example.ksp.data.model.modelresponse.NewPasswordResponse
import com.example.ksp.data.model.modelresponse.RegisterResponse
import com.example.ksp.data.model.modelresponse.VerifyResponse
import com.example.ksp.data.repository.datasource.KSPRemoteDataSource
import com.example.ksp.data.util.Resource
import com.example.ksp.domain.repository.KSPRepository
import retrofit2.Response
import javax.inject.Inject

class KSPRepositoryImpl @Inject constructor(
    private val kspRemoteDataSource: KSPRemoteDataSource
) : KSPRepository {

    private fun responseToRegisterResult(response: Response<RegisterResponse>) : Resource<RegisterResponse>{
        if(response.isSuccessful){
            if (response.body()?.success == true){
                response.body()?.let { result ->
                    Log.d("MainActivity", "${Resource.Success(result).data}")
                    return Resource.Success(result)
                }
            } else if(response.body()?.success == false){
                response.body()?.let {
                    Log.d("MainActivity", "${response.body()?.message}")
                    return Resource.Error(message = "${response.body()?.message}")
                }
            }
        }
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }

    private fun responseToString(response: Response<LoginResponse>) : Resource<LoginResponse>{
        if(response.isSuccessful){
            if(response.body()?.success == true) {
                response.body()?.let {
                    Log.d("MainActivity", "${Resource.Success(it).data}")
                    return Resource.Success(it)
                }
            } else if(response.body()?.success == false){
                response.body()?.let {
                    Log.d("MainActivity", "${response.body()?.message}")
                    return Resource.Error(message = "${response.body()?.message}")
                }
            }

        }
        Log.d("MainActivity","${response.errorBody()?.string()}")
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }

    private fun responseToVerify(response : Response<VerifyResponse>) : Resource<VerifyResponse>{
        if (response.isSuccessful){
            if(response.body()?.success == true){
                response.body()?.let {
                    Log.d("MainActivity", "${Resource.Success(it).data}")
                    return Resource.Success(it)
                }
            } else if(response.body()?.success == false){
                response.body().let {
                    Log.d("MainActivity", "${response.body()?.message}")
                    return Resource.Error(message = "${response.body()?.message}")
                }
            }

        }
        Log.d("MainActivity", "${response.errorBody()?.string()}")
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }

    private fun responseToNewPassword(response: Response<NewPasswordResponse>) : Resource<NewPasswordResponse>{
        if(response.isSuccessful){
            if (response.body()?.success == true){
                response.body()?.let { result ->
                    Log.d("MainActivity", "${Resource.Success(result).data}")
                    return Resource.Success(result)
                }
            } else if(response.body()?.success == false){
                response.body()?.let {
                    Log.d("MainActivity", "${response.body()?.message}")
                    return Resource.Error(message = "${response.body()?.message}")
                }
            }
        }
        Log.d("MainActivity", "${response.errorBody()?.string()}")
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }

    override suspend fun performLogin(login: LoginRequest): Resource<LoginResponse> {
        return responseToString(kspRemoteDataSource.performLogin(login = login))
    }

    override suspend fun performRegister(register: RegisterRequest): Resource<RegisterResponse> {
        return responseToRegisterResult(kspRemoteDataSource.performRegister(register = register))
    }

    override suspend fun performVerify(verify: VerifyRequest): Resource<VerifyResponse> {
        return responseToVerify(kspRemoteDataSource.performVerify(verify = verify))
    }

    override suspend fun updatePassword(password: NewPasswordRequest): Resource<NewPasswordResponse> {
        return responseToNewPassword(kspRemoteDataSource.updatePassword(password = password))
    }
}