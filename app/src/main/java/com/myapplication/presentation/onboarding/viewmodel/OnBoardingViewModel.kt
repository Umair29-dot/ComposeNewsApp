package com.myapplication.presentation.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.domain.usecases.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
	private val useCases: AppEntryUseCases
): ViewModel() {

	fun saveAppEntry() {
		viewModelScope.launch {
			useCases.saveAppEntry.invoke()
		}
	}

}