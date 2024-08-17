package com.myapplication.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.myapplication.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val useCases: NewsUseCases
): ViewModel() {

	fun getNews() {
		viewModelScope.launch {
			useCases.getNews(sources = listOf("bbc-news", "abc-news", "al-jazeera")).cachedIn(viewModelScope)
		}
	}

}