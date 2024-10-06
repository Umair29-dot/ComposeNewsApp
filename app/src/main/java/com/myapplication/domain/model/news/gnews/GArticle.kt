package com.myapplication.domain.model.news.gnews

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myapplication.domain.model.news.CommonArticle
import com.myapplication.domain.model.news.CommonSource

@Entity
data class GArticle(
	val content:String?,
	override val description:String?,
	override val publishedAt:String?,
	override val title:String?,
	@PrimaryKey override  val url:String,
	override val image:String?,
	val source: GSource?
): CommonArticle {
	override fun getArticleSource(): CommonSource? = source

}
