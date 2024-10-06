package com.myapplication.domain.model.news

interface CommonArticle {
	val description: String?
	val publishedAt: String?
	val title: String?
	val url: String
	val image: String?

	fun getArticleSource(): CommonSource?
}

interface CommonSource {
	val name: String?
}