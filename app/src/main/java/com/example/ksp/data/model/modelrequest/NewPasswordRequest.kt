package com.example.ksp.data.model.modelrequest

data class NewPasswordRequest(
    val password: String,
    val phone: String,
    val dob: String,
    val ic: Int
)
