package com.example.ksp.data.model.modelrequest

data class CreateCarRequest(
    val car_plate: String,
    val car_brand: String,
    val car_color: String,
    val account_id: Int
)
