package com.myapplication.presentation.main.detail.components

import androidx.lifecycle.ViewModel
import com.myapplication.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel  @Inject constructor(
	private val useCases: NewsUseCases
): ViewModel() {



}