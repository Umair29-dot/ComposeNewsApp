package com.myapplication.domain.usecases.news

import com.myapplication.domain.model.news.gnews.GArticle
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class UpsertGArticle(
	private val newsResponse: NewsRepository
) {

	suspend operator fun invoke(article: GArticle): Long {
		return newsResponse.upsertGArticle(article)
	}

}