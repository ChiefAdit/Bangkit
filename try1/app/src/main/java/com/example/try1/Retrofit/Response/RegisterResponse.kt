package com.example.try1.Retrofit.Response


import com.example.try1.Retrofit.Data.Data
data class RegisterResponse(
    val code: Int,
    val `data`: Data,
    val message: String,
    val success: Boolean
)