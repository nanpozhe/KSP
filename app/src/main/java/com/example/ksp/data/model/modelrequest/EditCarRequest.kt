package com.example.ksp.data.model.modelrequest

data class EditCarRequest(
    val car_plate: String,
    val car_brand: String,
    val car_color: String,
    val car_id: Int,
    val account_id: Int
)