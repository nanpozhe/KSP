package com.example.ksp.data.model

data class ValidationResult(
    val successful: Boolean,
    val error: String? = null
)
