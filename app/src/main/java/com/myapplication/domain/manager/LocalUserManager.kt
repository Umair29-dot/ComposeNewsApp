package com.myapplication.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

	//used for sharedPreferences
	suspend fun saveAppEntry()

	fun readAppEntry(): Flow<Boolean>

}