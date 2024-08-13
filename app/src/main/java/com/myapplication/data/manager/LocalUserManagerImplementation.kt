package com.myapplication.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.myapplication.domain.manager.LocalUserManager
import com.myapplication.util.Constants
import com.myapplication.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImplementation(private val context: Context): LocalUserManager {

	override suspend fun saveAppEntry() {
		context.dataStore.edit {
			it[PreferencesKey.APP_PREFERENCES_KEY] = true
		}
	}

	override fun readAppEntry(): Flow<Boolean> {
		return context.dataStore.data.map {
			it[PreferencesKey.APP_PREFERENCES_KEY] ?: false
		}
	}

}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKey {
	val APP_PREFERENCES_KEY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}