package com.myapplication.domain.usecases.news

import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class UpsertArticle(
	private val newsResponse: NewsRepository
) {

	suspend operator fun invoke(article: Article): Long {
		return newsResponse.upsertArticle(article)
	}

}