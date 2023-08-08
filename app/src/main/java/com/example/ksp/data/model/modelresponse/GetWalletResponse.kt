package com.example.ksp.data.model.modelresponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetWalletResponse(
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("wallet_id")
    var wallet_id: Int
) : Serializable
