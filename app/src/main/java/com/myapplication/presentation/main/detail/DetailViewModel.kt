package com.myapplication.presentation.main.detail

import android.util.Log
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.domain.model.news.CommonArticle
import com.myapplication.domain.model.news.gnews.GArticle
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.domain.usecases.news.NewsUseCases
import com.myapplication.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
	private val useCases: NewsUseCases
): ViewModel() {

	private val LOG = "DetailViewModel"

	private var _result = MutableSharedFlow<Long>()
	val result = _result.asSharedFlow()

	fun saveArticle(article: CommonArticle) {
		when(article) {
			is Article -> {
				saveArticleInDB(article)
			}

			is GArticle -> {
				saveGArticleInDB(article)
			}
		}
	}

	private fun saveArticleInDB(article: Article) {
		viewModelScope.launch(Dispatchers.IO) {
			try {
				val result = useCases.upsertArticle(article)
				withContext(Dispatchers.Main) {
					_result.emit(result)
				}
			} catch (e: Exception) {
				Log.d(LOG, e.message.toString())
			}
		}
	}

	private fun saveGArticleInDB(article: GArticle) {
		viewModelScope.launch(Dispatchers.IO) {
			try {
				val result = useCases.upsertGArticle(article)
				withContext(Dispatchers.Main) {
					_result.emit(result)
				}
			} catch (e: Exception) {
				Log.d(LOG, e.message.toString())
			}
		}
	}

}