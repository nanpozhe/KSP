package com.example.ksp.data.repository

import android.util.Log
import com.example.ksp.data.model.modelrequest.*
import com.example.ksp.data.model.modelresponse.*
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
        if(response.isSuccessful) {
            if (response.body()?.success == true) {
                response.body()?.let {
                    Log.d("MainActivity", "${Resource.Success(it).data}")
                    return Resource.Success(it)
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
            }
        }
        Log.d("MainActivity", "${response.errorBody()?.string()}")
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }

    private fun responseToWalletID(response: Response<GetWalletResponse>) : Resource<GetWalletResponse>{
        if(response.isSuccessful){
            if(response.body()?.success == true){
                response.body()?.let { result ->
                    Log.d("MainActivity", "${Resource.Success(result).data}")
                    return Resource.Success(result)
                }
            }
        }
        Log.d("MainActivity", "${response.errorBody()?.string()}")
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }

    private fun responseToWalletBalance(response: Response<GetBalanceResponse>) : Resource<GetBalanceResponse>{
        if (response.isSuccessful){
            if(response.body()?.success == true){
                response.body()?.let { result ->
                    Log.d("MainActivity", "${Resource.Success(result).data}")
                    return Resource.Success(result)
                }
            }
        }
        Log.d("MainActivity", "${response.errorBody()?.string()}")
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }

    private fun responseToTopUp(response: Response<TopUpResponse>) : Resource<TopUpResponse>{
        if(response.isSuccessful){
            if(response.body()?.success == true){
                response.body()?.let { result ->
                    Log.d("MainActivity", "${Resource.Success(result).data}")
                    return Resource.Success(result)
                }
            }
        }
        Log.d("MainActivity", "${response.errorBody()?.string()}")
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }

    private fun simpleCarResponse(response: Response<CarResponse>) : Resource<CarResponse>{
        if(response.isSuccessful){
            if(response.body()?.success == true){
                response.body()?.let { result ->
                    Log.d("MainActivity", "${Resource.Success(result).data}")
                    return Resource.Success(result)
                }
            }
        }
        Log.d("MainActivity", "${response.errorBody()?.string()}")
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }

    private fun responseToCarData(response: Response<GetAllCarResponse>) : Resource<GetAllCarResponse>{
        if(response.isSuccessful){
            if(response.body()?.success == true){
                response.body()?.let { result ->
                    Log.d("MainActivity", "${Resource.Success(result).data}")
                    return Resource.Success(result)
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

    override suspend fun getWalletID(getWallet: GetWalletRequest): Resource<GetWalletResponse> {
        return responseToWalletID(kspRemoteDataSource.getWalletID(getWallet = getWallet))
    }

    override suspend fun getWalletBalance(getBalance: GetBalanceRequest): Resource<GetBalanceResponse> {
        return responseToWalletBalance(kspRemoteDataSource.getWalletBalance(getBalance = getBalance))
    }

    override suspend fun topUp(topUp: TopUpRequest): Resource<TopUpResponse> {
        return responseToTopUp(kspRemoteDataSource.topUp(topUp = topUp))
    }

    override suspend fun createCar(createCar: CreateCarRequest) : Resource<CarResponse> {
        return simpleCarResponse(kspRemoteDataSource.createCar(createCar = createCar))
    }
    override suspend fun editCar(editCar: EditCarRequest) : Resource<CarResponse> {
        return simpleCarResponse(kspRemoteDataSource.editCar(editCar = editCar))
    }
    override suspend fun deleteCar(deleteCar: DeleteCarRequest) : Resource<CarResponse> {
        return simpleCarResponse(kspRemoteDataSource.deleteCar(deleteCar = deleteCar))
    }
    override suspend fun getAllCar(getAllCar: GetAllCarRequest) : Resource<GetAllCarResponse> {
        return responseToCarData(kspRemoteDataSource.getAllCar(getAllCar = getAllCar))
    }
}