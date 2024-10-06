package com.myapplication.domain.model.news.newsapi

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myapplication.domain.model.news.CommonArticle
import com.myapplication.domain.model.news.CommonSource

@Entity
data class Article(
	val author: String?,
	override val description: String?,
	override val publishedAt: String?,
	override val title: String?,
	@PrimaryKey override val url: String,
	val content:String?,
	val urlToImage:String?,
	val source: Source?
): CommonArticle {

	override val image: String?
		get() = urlToImage
	override fun getArticleSource(): CommonSource? = source

}