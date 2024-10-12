package com.myapplication.domain.usecases.news

data class NewsUseCases(
	val getNews: GetNews,
	val getGNews: GetGNews,
	val getSearchNews: GetSearchNews,
	val upsertArticle: UpsertArticle,
	val deleteArticle: DeleteArticle,
	val getArticles: GetArticles,
	val getGArticles: GetGArticles,
	val upsertGArticle: UpsertGArticle
)