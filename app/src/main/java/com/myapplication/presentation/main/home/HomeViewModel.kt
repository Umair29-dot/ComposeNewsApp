package com.myapplication.presentation.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.myapplication.domain.model.news.gnews.GArticle
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val useCases: NewsUseCases
): ViewModel() {

	private val LOG = "homeViewModel"

	private var _gNews: MutableStateFlow<List<GArticle>> = MutableStateFlow(emptyList())
	val gNews = _gNews.asStateFlow()

	private var _news: MutableStateFlow<PagingData<Article>> = MutableStateFlow(PagingData.empty())
	val news = _news.asStateFlow()

	init {
		getGNews()
	}

	fun getGNews() {
		viewModelScope.launch {
			try {
				val articles = useCases.getGNews()
				_gNews.value = articles

				if (!articles.isNullOrEmpty()) {
					getNews()
				}
			} catch (e: Exception) {
				Log.d(LOG, e.message.toString())
			}
		}
	}

	fun getNews() {
		viewModelScope.launch {
			try {
				useCases.getNews()
					.cachedIn(viewModelScope)
					.collect {
						_news.value = it
					}
			} catch (e: Exception) {
				Log.d(LOG, e.message.toString())
			}
		}
	}

}