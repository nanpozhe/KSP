package com.example.ksp.data.model.modelresponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetAllCarResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("car_id_1")
    val car_id_1: Int,
    @SerializedName("car_plate_1")
    val car_plate_1: String,
    @SerializedName("car_id_2")
    val car_id_2: Int,
    @SerializedName("car_plate_2")
    val car_plate_2: String,
    @SerializedName("car_id_3")
    val car_id_3: Int,
    @SerializedName("car_plate_3")
    val car_plate_3: String,
    @SerializedName("car_id_4")
    val car_id_4: Int,
    @SerializedName("car_plate_4")
    val car_plate_4: String,
    @SerializedName("car_id_5")
    val car_id_5: Int,
    @SerializedName("car_plate_5")
    val car_plate_5: String
) : Serializable
