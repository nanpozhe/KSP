package com.example.ksp.data.model.modelrequest

data class ParkNPayRequest(
    val pp_amount: Double,
    val pp_duration: Int,
    val wallet_id: Int,
    val car_id: Int,
    val council_name: String
)
