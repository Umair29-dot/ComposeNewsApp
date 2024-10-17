package com.myapplication.domain.usecases.news

import com.myapplication.domain.model.news.CommonArticle
import com.myapplication.domain.model.news.gnews.GArticle
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.domain.repository.NewsRepository

class DeleteGArticle(
	private val newsRepository: NewsRepository
) {

	suspend operator fun invoke(article: GArticle): Int {
		return newsRepository.deleteGArticle(article = article)
	}

}