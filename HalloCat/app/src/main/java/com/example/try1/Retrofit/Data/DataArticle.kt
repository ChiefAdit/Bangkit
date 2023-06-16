package com.example.try1.Retrofit.Data

import com.example.try1.Retrofit.Request.Image

data class DataArticle(
    val id: Int,
    val user_id: Int,
    val category_id: Int,
    val title: String,
    val slug: String,
    val short_description: String,
    val description: String,
    val status: String,
    val finished_at: String,
    val images: List<Image>
)
