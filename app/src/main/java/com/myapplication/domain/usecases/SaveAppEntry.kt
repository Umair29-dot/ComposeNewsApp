package com.myapplication.domain.usecases

import com.myapplication.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class SaveAppEntry(
	private val localUserManager: LocalUserManager
) {
	suspend operator fun invoke() {
		localUserManager.saveAppEntry()
	}
}