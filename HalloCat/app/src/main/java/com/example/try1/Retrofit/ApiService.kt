package com.example.try1.Retrofit

import com.example.try1.Retrofit.Request.ArticleRequest
import com.example.try1.Retrofit.Request.LoginRequest
import com.example.try1.Retrofit.Request.RegisterRequest
import com.example.try1.Retrofit.Response.ArticleResponse
import com.example.try1.Retrofit.Response.LoginResponse
import com.example.try1.Retrofit.Response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {



    @POST(Constans.LOGIN_URL)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST(Constans.REGISTER_URL)
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @GET(Constans.ARTICLE_URL)
    fun article(@Body request: ArticleRequest): Call<ArticleResponse>
}