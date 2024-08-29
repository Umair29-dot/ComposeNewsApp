package com.myapplication.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myapplication.domain.model.news.newsapi.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(article: Article)

	@Delete
	suspend fun delete(article: Article)

	@Query("SELECT * FROM Article")
	fun getArticles(): Flow<List<Article>>

}