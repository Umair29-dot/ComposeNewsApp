package com.myapplication.domain.usecases.news

import com.myapplication.domain.model.news.gnews.GArticle
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetGArticles(
	private val newsResponse: NewsRepository
) {

	operator fun invoke(): Flow<List<GArticle>> {
		return newsResponse.getGArticles()
	}

}