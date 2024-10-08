package com.myapplication.domain.usecases.app_entry

import com.myapplication.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
	private val localUserManager: LocalUserManager
) {
	operator fun invoke(): Flow<Boolean> {
		return localUserManager.readAppEntry()
	}
}