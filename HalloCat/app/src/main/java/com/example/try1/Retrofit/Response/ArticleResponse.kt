package com.example.try1.Retrofit.Response

import com.example.try1.Retrofit.Data.DataArticle

class ArticleResponse (
    val code: Int,
    val data: List<DataArticle>,
    val message: String,
    val success: Boolean
)
