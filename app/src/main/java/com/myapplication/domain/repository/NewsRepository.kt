package com.myapplication.domain.repository

import androidx.paging.PagingData
import com.myapplication.domain.nmodel.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

	fun getNews(sources: List<String>): Flow<PagingData<Article>>  // PagingData comes from library

}