package com.myapplication.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myapplication.domain.model.news.gnews.GArticle
import com.myapplication.domain.model.news.newsapi.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertArticle(article: Article): Long

	@Delete
	suspend fun deleteArticle(article: Article): Int

	@Query("SELECT * FROM Article")
	fun getArticles(): Flow<List<Article>>


	//GNews
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertGArticle(article: GArticle): Long

	@Delete
	suspend fun deleteGArticle(article: GArticle): Int

	@Query("SELECT * FROM GArticle")
	fun getGArticles(): Flow<List<GArticle>>
}