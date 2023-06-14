package com.example.try1.Retrofit.Request

import com.google.gson.annotations.SerializedName

data class ArticleRequest(

	@field:SerializedName("short_description")
	val shortDescription: String? = null,

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("finished_at")
	val finishedAt: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
