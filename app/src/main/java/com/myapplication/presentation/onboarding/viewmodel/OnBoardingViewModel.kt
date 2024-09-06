package com.myapplication.presentation.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
	private val useCases: AppEntryUseCases
): ViewModel() {

	private val LOG = "OnBoardingViewModel"

	fun saveAppEntry(onResult: (Boolean) -> Unit) {
		viewModelScope.launch(Dispatchers.IO) {
			try {
				useCases.saveAppEntry.invoke()
				withContext(Dispatchers.Main) {
					onResult(true)
				}
			} catch (e: Exception) {
				withContext(Dispatchers.Main) {
					onResult(false)
				}
				Log.d(LOG, e.message.toString())
			}
		}
	}

}