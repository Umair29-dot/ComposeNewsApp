package com.myapplication.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.myapplication.BuildConfig
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.util.Constants

class SearchNewsPagingSource(
	private val newsApi: NewsApi,
	private val sources: String,
	private val searchQuery: String
): PagingSource<Int, Article>() {

	private var totalNewsCount = 0

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
		val page = params.key ?: 1

		return try {
			val newsResponse = newsApi.searchNews(searchQuery = searchQuery, page = page, sources = sources, apiKey = BuildConfig.Api_KEY)
			totalNewsCount += newsResponse.articles.size
			val articles = newsResponse.articles.distinctBy { it.title } //remove duplicate data from list
			LoadResult.Page(
				data = articles,
				nextKey = if(totalNewsCount == newsResponse.totalResults) null else page + 1,
				prevKey = null
			)
		} catch (e: Exception) {
			e.printStackTrace()
			LoadResult.Error(throwable = e)
		}
	}

	override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			val anchorPage = state.closestPageToPosition((anchorPosition))
			anchorPage?.nextKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
		}
	}
}