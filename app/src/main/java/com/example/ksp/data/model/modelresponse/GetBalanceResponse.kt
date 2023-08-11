package com.example.ksp.data.model.modelresponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetBalanceResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("amount")
    var amount: Float
) : Serializable
