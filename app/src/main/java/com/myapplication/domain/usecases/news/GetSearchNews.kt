package com.myapplication.domain.usecases.news

import androidx.paging.PagingData
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSearchNews(
	private val repository: NewsRepository
) {

	operator fun invoke(searchQuery: String): Flow<PagingData<Article>> {
		return repository.getSearchNews(searchQuery)
	}

}