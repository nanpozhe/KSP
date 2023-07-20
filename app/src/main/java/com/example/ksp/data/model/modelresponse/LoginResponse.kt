package com.example.ksp.data.model.modelresponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("fullname")
    val fullname: String
) : Serializable
