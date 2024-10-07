package com.myapplication.presentation.main.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
	private val newsUseCases: NewsUseCases
): ViewModel() {

	private val _articles = MutableStateFlow<List<Article>>(emptyList())
	val articles: StateFlow<List<Article>> = _articles.asStateFlow()

	/*init {
		getArticles()
	}*/

	private fun getArticles() {
		viewModelScope.launch {
			newsUseCases.getArticles().collect {
				_articles.value = it
			}
		}
	}

	fun deleteArticle(article: Article) {
		viewModelScope.launch {
			newsUseCases.deleteArticle(article)
		}
	}

	fun upsertArticle(article: Article) {
		viewModelScope.launch {
			newsUseCases.upsertArticle(article)
		}
	}
}