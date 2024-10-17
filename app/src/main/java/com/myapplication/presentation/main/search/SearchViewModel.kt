package com.myapplication.presentation.main.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
	private val useCases: NewsUseCases
): ViewModel() {

	private val LOG = "searchViewModel"

	private var _search: MutableStateFlow<PagingData<Article>> = MutableStateFlow(PagingData.empty())
	val search: StateFlow<PagingData<Article>> = _search

	init {
		searchNews(searchQuery = "all")
	}

	fun searchNews(searchQuery: String) {
		viewModelScope.launch {
			try {
				useCases.getSearchNews(searchQuery = searchQuery)
					.cachedIn(viewModelScope)
					.collect {
						_search.value = it
					}
			}catch (e: Exception) {
				Log.d(LOG, e.message.toString())
			}
		}
	}

}