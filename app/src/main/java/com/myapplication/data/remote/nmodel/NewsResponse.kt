package com.myapplication.data.remote.nmodel

import com.myapplication.domain.nmodel.Article

data class NewsResponse (
	val articles: List<Article>,
	val status: String,
	val totalResults: Int
		)