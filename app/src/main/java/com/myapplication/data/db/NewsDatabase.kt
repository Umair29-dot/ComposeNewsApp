package com.myapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.myapplication.domain.model.news.CommonArticle
import com.myapplication.domain.model.news.gnews.GArticle
import com.myapplication.domain.model.news.newsapi.Article

@Database(entities = [Article::class, GArticle::class], version = 1)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase: RoomDatabase() {

	abstract val newsDao: NewsDao

}