package com.myapplication.domain.usecases.news

import com.myapplication.domain.model.news.CommonArticle
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.domain.repository.NewsRepository

class DeleteArticle(
	private val newsRepository: NewsRepository
) {

	suspend operator fun invoke(article: Article): Int {
		return newsRepository.deleteArticle(article = article)
	}

}