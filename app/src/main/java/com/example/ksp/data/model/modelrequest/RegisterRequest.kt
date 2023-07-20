package com.example.ksp.data.model.modelrequest

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RegisterRequest(
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("phoneNum")
    val phone: String,
    @SerializedName("dob")
    val dob: String,
    @SerializedName("ic")
    val ic: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
) : Serializable
