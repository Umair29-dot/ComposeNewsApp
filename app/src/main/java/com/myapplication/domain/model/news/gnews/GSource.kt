package com.myapplication.domain.model.news.gnews

import com.myapplication.domain.model.news.CommonSource

data class GSource(
	override val name: String?,
	val url: String?
): CommonSource
