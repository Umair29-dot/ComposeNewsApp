package com.myapplication.data.di

import android.app.Application
import androidx.compose.ui.unit.Constraints
import androidx.room.Room
import com.myapplication.data.db.NewsDao
import com.myapplication.data.db.NewsDatabase
import com.myapplication.data.db.NewsTypeConverter
import com.myapplication.data.manager.LocalUserManagerImplementation
import com.myapplication.data.remote.GNewsApi
import com.myapplication.data.remote.NewsApi
import com.myapplication.data.repository.NewsRepositoryImplementation
import com.myapplication.domain.manager.LocalUserManager
import com.myapplication.domain.repository.NewsRepository
import com.myapplication.domain.usecases.app_entry.AppEntryUseCases
import com.myapplication.domain.usecases.app_entry.ReadAppEntry
import com.myapplication.domain.usecases.app_entry.SaveAppEntry
import com.myapplication.domain.usecases.news.DeleteArticle
import com.myapplication.domain.usecases.news.GetArticles
import com.myapplication.domain.usecases.news.GetGNews
import com.myapplication.domain.usecases.news.GetNews
import com.myapplication.domain.usecases.news.GetSearchNews
import com.myapplication.domain.usecases.news.NewsUseCases
import com.myapplication.domain.usecases.news.UpsertArticle
import com.myapplication.domain.usecases.news.UpsertGArticle
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
	fun provideAppEntryUseCases(localUserManager: LocalUserManager) =
		AppEntryUseCases(
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

	@Provides
	@Singleton
	fun provideGNewsApi(): GNewsApi = Retrofit.Builder()
		.baseUrl(Constants.BASE_URL_GNEWS)
		.addConverterFactory(GsonConverterFactory.create())
		.build()
		.create(GNewsApi::class.java)

	@Singleton
	@Provides
	fun provideNewsRepository(newsApi: NewsApi, newsDao: NewsDao, gNewApi: GNewsApi): NewsRepository =
		NewsRepositoryImplementation(newsApi, newsDao, gNewApi)

	@Singleton
	@Provides
	fun provideNewsUseCases(newsRepository: NewsRepository): NewsUseCases =
		NewsUseCases(
			getNews = GetNews(newsRepository),
			getSearchNews = GetSearchNews(newsRepository),
			upsertArticle = UpsertArticle(newsRepository),
			deleteArticle = DeleteArticle(newsRepository),
			getArticles = GetArticles(newsRepository),
			getGNews = GetGNews(newsRepository),
			upsertGArticle = UpsertGArticle(newsRepository)
		)

	@Singleton
	@Provides
	fun provideNewsDB(application: Application): NewsDatabase =
		Room.databaseBuilder(
			context = application,
			klass = NewsDatabase::class.java,
	        name = Constants.NEWS_DB_NAME
		).addTypeConverter(NewsTypeConverter())
			.fallbackToDestructiveMigration()
			.build()

	@Singleton
	@Provides
	fun provideNewsDao(db: NewsDatabase): NewsDao = db.newsDao
}