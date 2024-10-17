package com.myapplication.presentation.main.bookmark

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.domain.model.news.CommonArticle
import com.myapplication.domain.model.news.gnews.GArticle
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.domain.usecases.news.NewsUseCases
import com.myapplication.util.ResourceResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
	private val newsUseCases: NewsUseCases
): ViewModel() {

	private val LOG = "bookmarkViewModel"

	private val _savedArticles: MutableStateFlow<ResourceResponse<List<CommonArticle>>> = MutableStateFlow(ResourceResponse.ideal())
	val savedArticles = _savedArticles.asStateFlow()

	private val _articleDelete: MutableSharedFlow<Int> = MutableSharedFlow()
	val articleDelete = _articleDelete.asSharedFlow()

	init {
		getArticles()
	}

	private fun getArticles() {
		_savedArticles.value = ResourceResponse.Loading()

		viewModelScope.launch(Dispatchers.IO) {
			delay(2000)

			try {
				combine(
					newsUseCases.getArticles(),     // Flow<List<Article>>
					newsUseCases.getGArticles()     // Flow<List<GArticle>>
				) { articlesList, gArticlesList ->
					val articleItems = articlesList.map { it as CommonArticle }
					val gArticleItems = gArticlesList.map { it as CommonArticle }
					articleItems + gArticleItems  // Return the combined list
				}.collectLatest {
					withContext(Dispatchers.Main) {
						_savedArticles.value = ResourceResponse.Success(it)
					}
				}
			} catch (e: Exception) {
				withContext(Dispatchers.Main) {
					_savedArticles.value = ResourceResponse.Error(e.message.toString())
				}
			}
		}
	}

	fun deleteArticle(article: CommonArticle) {
		viewModelScope.launch(Dispatchers.IO) {
			try {
				when(article) {
					is Article -> {
						val result = newsUseCases.deleteArticle(article)
						withContext(Dispatchers.Main) {
							_articleDelete.emit(result)
						}
					}
					is GArticle -> {
						val result = newsUseCases.deleteGArticle(article)
						withContext(Dispatchers.Main) {
							_articleDelete.emit(result)
						}
					}
				}
			} catch (e: Exception) {
				Log.d(LOG, e.message.toString())
			}
		}
	}

	fun upsertArticle(article: Article) {
		viewModelScope.launch {
			newsUseCases.upsertArticle(article)
		}
	}
}