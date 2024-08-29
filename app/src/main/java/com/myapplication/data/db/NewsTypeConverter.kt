package com.myapplication.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.myapplication.domain.model.news.newsapi.Source

@ProvidedTypeConverter
class NewsTypeConverter {

	@TypeConverter
	fun sourceToString(source: Source): String {
		return "${source.id}, ${source.name}"
	}

	@TypeConverter
	fun stringToSource(source: String): Source {
		val arr = source.split(",")
		return  Source(id = arr[0], name = arr[1])
	}

}