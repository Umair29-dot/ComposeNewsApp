package com.myapplication.domain.usecases.news

data class NewsUseCases(
	val getNews: GetNews,
	val getSearchNews: GetSearchNews,
	val upsertArticle: UpsertArticle,
	val deleteArticle: DeleteArticle,
	val getArticles: GetArticles,
	val getGNews: GetGNews,
	val upsertGArticle: UpsertGArticle
)