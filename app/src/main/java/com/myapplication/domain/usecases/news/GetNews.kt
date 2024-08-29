package com.myapplication.domain.usecases.news

import androidx.paging.PagingData
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
	private val newsRepository: NewsRepository
) {

	operator fun invoke (sources: List<String>): Flow<PagingData<Article>> {
		return newsRepository.getNews(sources)
	}

}