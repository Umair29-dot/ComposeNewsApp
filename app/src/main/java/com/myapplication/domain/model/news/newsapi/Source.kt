package com.myapplication.domain.model.news.newsapi

import com.myapplication.domain.model.news.CommonSource

data class Source(
	val id: Any?,
	override val name:String?
): CommonSource
