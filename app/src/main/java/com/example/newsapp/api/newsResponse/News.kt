package com.example.newsapp.api.newsResponse

import com.example.newsapp.api.model.sourcesResponse.Source
import com.google.gson.annotations.SerializedName

 class News(

	 @field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	 @field:SerializedName("author")
	val author: Any? = null,

	 @field:SerializedName("urlToImage")
	val urlToImage: String? = null,

	 @field:SerializedName("description")
	val description: String? = null,

	 @field:SerializedName("source")
	val source: Source? = null,

	 @field:SerializedName("title")
	val title: String? = null,

	 @field:SerializedName("url")
	val url: String? = null,

	 @field:SerializedName("content")
	val content: String? = null
)