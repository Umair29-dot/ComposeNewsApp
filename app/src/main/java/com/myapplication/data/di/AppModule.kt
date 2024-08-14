package com.myapplication.data.di

import android.app.Application
import com.myapplication.data.manager.LocalUserManagerImplementation
import com.myapplication.domain.manager.LocalUserManager
import com.myapplication.domain.usecases.AppEntryUseCases
import com.myapplication.domain.usecases.ReadAppEntry
import com.myapplication.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Singleton
	@Provides
	fun provideLocalUserManager(application: Application): LocalUserManager = LocalUserManagerImplementation(application)
	@Singleton
	@Provides
	fun provideAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUseCases(
		readAppEntry = ReadAppEntry(localUserManager),
		saveAppEntry = SaveAppEntry(localUserManager)
	)
}