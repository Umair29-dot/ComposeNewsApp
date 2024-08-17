package com.myapplication.data.di

import android.app.Application
import androidx.compose.ui.unit.Constraints
import com.myapplication.data.manager.LocalUserManagerImplementation
import com.myapplication.data.remote.NewsApi
import com.myapplication.data.repository.NewsRepositoryImplementation
import com.myapplication.domain.manager.LocalUserManager
import com.myapplication.domain.repository.NewsRepository
import com.myapplication.domain.usecases.app_entry.AppEntryUseCases
import com.myapplication.domain.usecases.app_entry.ReadAppEntry
import com.myapplication.domain.usecases.app_entry.SaveAppEntry
import com.myapplication.domain.usecases.news.GetNews
import com.myapplication.domain.usecases.news.NewsUseCases
import com.myapplication.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

	@Provides
	@Singleton
	fun provideNewsApi(): NewsApi = Retrofit.Builder()
		.baseUrl(Constants.BASE_URL)
		.addConverterFactory(GsonConverterFactory.create())
		.build()
		.create(NewsApi::class.java)

	@Singleton
	@Provides
	fun provideNewsRepository(newsApi: NewsApi): NewsRepository = NewsRepositoryImplementation(newsApi)

	@Singleton
	@Provides
	fun provideNewsUseCases(newsRepository: NewsRepository) = NewsUseCases(
		getNews = GetNews(newsRepository)
	)
}