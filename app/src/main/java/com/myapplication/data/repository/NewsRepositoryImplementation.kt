package com.myapplication.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.myapplication.data.remote.NewsApi
import com.myapplication.data.remote.NewsPagingSource
import com.myapplication.domain.nmodel.Article
import com.myapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImplementation(
	private val newsApi: NewsApi
): NewsRepository {
	override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
		return Pager(
			config = PagingConfig(pageSize = 10),
			pagingSourceFactory = {
				NewsPagingSource(
					newsApi = newsApi,
					sources = sources.joinToString(",")
				)
			}
		).flow
	}
}