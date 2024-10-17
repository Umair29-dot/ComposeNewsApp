package com.myapplication.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.myapplication.data.db.NewsDao
import com.myapplication.data.remote.GNewsApi
import com.myapplication.data.remote.NewsApi
import com.myapplication.data.remote.NewsPagingSource
import com.myapplication.data.remote.SearchNewsPagingSource
import com.myapplication.domain.model.news.CommonArticle
import com.myapplication.domain.model.news.gnews.GArticle
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImplementation(
	private val newsApi: NewsApi,
	private val newsDao: NewsDao,
	private val gNewsApi: GNewsApi
): NewsRepository {
	override fun getNews(): Flow<PagingData<Article>> {
		return Pager(
			config = PagingConfig(pageSize = 10),
			pagingSourceFactory = {
				NewsPagingSource(
					newsApi = newsApi
				)
			}
		).flow
	}

	override fun getSearchNews(
		searchQuery: String
	): Flow<PagingData<Article>> {
		return Pager(
			config = PagingConfig(pageSize = 10),
			pagingSourceFactory = {
				SearchNewsPagingSource(
					searchQuery = searchQuery,
					newsApi = newsApi
				)
			}
		).flow
	}

	override suspend fun getGNews(): List<GArticle> {
		val response = gNewsApi.getNews()
		return response.articles
	}

	override suspend fun upsertArticle(article: Article): Long {
		return newsDao.upsertArticle(article)
	}

	override suspend fun upsertGArticle(article: GArticle): Long {
		return newsDao.upsertGArticle(article)
	}

	override suspend fun deleteArticle(article: Article): Int {
		return newsDao.deleteArticle(article)
	}

	override suspend fun deleteGArticle(article: GArticle): Int {
		return newsDao.deleteGArticle(article)
	}

	override fun getArticles(): Flow<List<Article>> {
		return newsDao.getArticles()
	}

	override fun getGArticles(): Flow<List<GArticle>> {
		return newsDao.getGArticles()
	}
}