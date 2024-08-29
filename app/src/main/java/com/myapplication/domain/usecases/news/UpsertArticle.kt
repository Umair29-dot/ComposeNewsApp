package com.myapplication.domain.usecases.news

import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.domain.repository.NewsRepository

class UpsertArticle(
	private val newsResponse: NewsRepository
) {

	suspend operator fun invoke(article: Article) {
		newsResponse.upsertArticle(article)
	}

}