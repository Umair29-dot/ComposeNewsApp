package com.myapplication.data.remote

import com.myapplication.data.remote.nmodel.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

	@GET("everything")
	suspend fun getNews(
		@Query("page") page: Int,
		@Query("sources") sources: String,
		@Query("apiKey") apiKey: String
	): NewsResponse

}