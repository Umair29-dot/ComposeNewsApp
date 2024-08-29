package com.myapplication.data.remote

import com.myapplication.data.remote.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

	@GET("everything")
	suspend fun getNews(
		@Query("page") page: Int,
		@Query("sources") sources: String,
		@Query("apiKey") apiKey: String
	): NewsResponse

	@GET("everything")
	suspend fun searchNews(
		@Query("q") searchQuery: String,
		@Query("page") page: Int,
		@Query("sources") sources: String,
		@Query("apiKey") apiKey: String
	): NewsResponse
}