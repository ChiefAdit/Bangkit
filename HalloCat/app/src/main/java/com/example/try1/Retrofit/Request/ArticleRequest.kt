package com.example.try1.Retrofit.Request

import com.example.try1.Retrofit.Data.DataArticle
import com.google.gson.annotations.SerializedName

data class ArticleRequest(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: List<DataArticle>,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)
