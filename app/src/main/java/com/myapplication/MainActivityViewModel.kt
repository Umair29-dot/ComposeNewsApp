package com.myapplication

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.domain.usecases.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
	private val useCases: AppEntryUseCases
): ViewModel() {

	var onBoardingCondition = mutableStateOf(false)

	init {
		checkOnBoardingCondition()
	}

	private fun checkOnBoardingCondition() {
		viewModelScope.launch {
			useCases.readAppEntry().collect {
				onBoardingCondition.value = it
			}
		}
	}

}