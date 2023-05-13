package com.example.newsapp.api

import com.example.newsapp.api.model.sourcesResponse.SourcesResponse
import com.example.newsapp.api.newsResponse.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface
WebService {

    //https://newsapi.org/

    @GET("v2/top-headlines/sources")
    fun getSources(@Query("apiKey") apiKey: String): Call<SourcesResponse>

    @GET("/v2/everything")
    fun getNews(
         @Query("apiKey") apiKey: String
         ,@Query("sources") sources: String
    ): Call<NewsResponse>
}