package com.myapplication.data.remote.model

import com.myapplication.domain.model.news.gnews.GArticle

data class GNewsResponse (
	val totalArticles: Int,
	val articles: List<GArticle>
		)