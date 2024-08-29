package com.myapplication.data.remote

import com.myapplication.BuildConfig
import com.myapplication.data.remote.model.GNewsResponse
import com.myapplication.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface GNewsApi {

	@GET("top-headlines")
	suspend fun getNews(
		@Query("category") category: String = "world",
		@Query("lang") lang: String = "en",
		@Query("apikey") apikey: String = BuildConfig.G_Api_KEY
	): GNewsResponse

}