package com.example.ksp.data.model.modelresponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VerifyResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("phoneNum")
    val phone: String,
    @SerializedName("dob")
    val dob: String,
    @SerializedName("ic")
    val ic: Int
) : Serializable
