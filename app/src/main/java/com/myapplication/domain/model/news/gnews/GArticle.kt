package com.myapplication.domain.model.news.gnews

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GArticle(
	val content:String?,
	val description:String?,
	val publishedAt:String?,
	val source: GSource?,
	val title:String?,
	@PrimaryKey val url:String,
	val image:String?
)
