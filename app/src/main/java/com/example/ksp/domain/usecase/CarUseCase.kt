package com.example.ksp.domain.usecase

import android.util.Log
import com.example.ksp.data.model.modelrequest.CreateCarRequest
import com.example.ksp.data.model.modelrequest.DeleteCarRequest
import com.example.ksp.data.model.modelrequest.EditCarRequest
import com.example.ksp.data.model.modelrequest.GetAllCarRequest
import com.example.ksp.data.model.modelresponse.CarResponse
import com.example.ksp.data.model.modelresponse.GetAllCarResponse
import com.example.ksp.data.util.Resource
import com.example.ksp.domain.repository.KSPRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CarUseCase @Inject constructor(
    private val kspRepository: KSPRepository
) {
    private val TAG = "MainActivity"
    private val CarUseCase = "CarUseCase"

    fun createCar(createCarRequest: CreateCarRequest) : Flow<Resource<CarResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = kspRepository.createCar(createCar = createCarRequest)
            Log.i(TAG, "$CarUseCase -> I am creating car, ${response.data?.message}")
            emit(response)
        } catch (e: HttpException) {
            Log.i(TAG, "$CarUseCase, creating car -> ${e.localizedMessage!!}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.i(TAG, "$CarUseCase, creating car -> ${e.localizedMessage!!}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    fun editCar(editCarRequest: EditCarRequest) : Flow<Resource<CarResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = kspRepository.editCar(editCar = editCarRequest)
            Log.i(TAG, "$CarUseCase -> I am editing car, ${response.data?.message}")
            emit(response)
        } catch (e: HttpException) {
            Log.i(TAG, "$CarUseCase, editing car -> ${e.localizedMessage!!}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.i(TAG, "$CarUseCase, editing car -> ${e.localizedMessage!!}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    fun deleteCar(deleteCarRequest: DeleteCarRequest) : Flow<Resource<CarResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = kspRepository.deleteCar(deleteCar = deleteCarRequest)
            Log.i(TAG, "$CarUseCase -> I am deleting car, ${response.data?.message}")
            emit(response)
        } catch (e: HttpException) {
            Log.i(TAG, "$CarUseCase, deleting car -> ${e.localizedMessage!!}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.i(TAG, "$CarUseCase, deleting car -> ${e.localizedMessage!!}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    fun getAllCarInfo(getAllCarRequest: GetAllCarRequest) : Flow<Resource<GetAllCarResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = kspRepository.getAllCar(getAllCar = getAllCarRequest)
            Log.i(TAG, "$CarUseCase -> I am retrieving car info, ${response.data?.message}")
            emit(response)
        } catch (e: HttpException) {
            Log.i(TAG, "$CarUseCase, retrieving car info -> ${e.localizedMessage!!}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.i(TAG, "$CarUseCase, retrieving car info -> ${e.localizedMessage!!}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}