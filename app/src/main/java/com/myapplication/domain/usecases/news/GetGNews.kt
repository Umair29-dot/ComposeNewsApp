package com.myapplication.domain.usecases.news

import com.myapplication.domain.model.news.gnews.GArticle
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.domain.repository.NewsRepository

class GetGNews(
	private val newsRepository: NewsRepository
) {

	suspend operator fun invoke(): List<GArticle> {
		return newsRepository.getGNews()
	}

}