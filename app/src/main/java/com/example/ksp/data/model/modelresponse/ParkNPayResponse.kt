package com.example.ksp.data.model.modelresponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ParkNPayResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
) : Serializable
