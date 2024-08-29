package com.myapplication.presentation

import androidx.lifecycle.ViewModel
import com.myapplication.domain.model.news.newsapi.Article

class SharedViewModel: ViewModel() {

	var article: Article? = null
		private set

	fun setArticle(article: Article){
		this.article = article
	}
}