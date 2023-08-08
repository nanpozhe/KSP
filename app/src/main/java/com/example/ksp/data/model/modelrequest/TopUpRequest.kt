package com.example.ksp.data.model.modelrequest

data class TopUpRequest(
    val amount: Int,
    val wallet_id: Int,
    val method: Int
)
