package com.myapplication.domain.repository

import androidx.paging.PagingData
import com.myapplication.domain.model.news.gnews.GArticle
import com.myapplication.domain.model.news.newsapi.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

	//NewsApi
	fun getNews(sources: List<String>): Flow<PagingData<Article>>  // PagingData comes from library
	fun getSearchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

	//GNews
	suspend fun getGNews(): List<GArticle>

	//Database
	suspend fun upsertArticle(article: Article): Long
	suspend fun upsertGArticle(article: GArticle): Long
	suspend fun deleteArticle(article: Article)
	fun getArticles(): Flow<List<Article>>
}