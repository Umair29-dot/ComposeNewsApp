package com.myapplication.data.remote.model

import com.myapplication.domain.model.news.newsapi.Article

data class NewsResponse (
	val articles: List<Article>,
	val status: String,
	val totalResults: Int
		)