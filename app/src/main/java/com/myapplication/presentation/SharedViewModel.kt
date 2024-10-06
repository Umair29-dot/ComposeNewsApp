package com.myapplication.presentation

import androidx.lifecycle.ViewModel
import com.myapplication.domain.model.news.CommonArticle
import com.myapplication.domain.model.news.newsapi.Article

class SharedViewModel: ViewModel() {

	var article: CommonArticle? = null
		private set

	fun setArticle(article: CommonArticle){
		this.article = article
	}
}