package com.myapplication.presentation.main.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.presentation.Dimens

@Composable
fun ArticleList(
	articles: List<Article>,
	onClick: (Article) -> Unit = {}
) {
	LazyColumn(
		modifier = Modifier.fillMaxSize(),
		contentPadding = PaddingValues(all = Dimens.SmallPadding1)
	) {
		items(count = articles.size) {
			articles[it]?.let {
				ArticleCard(article = it) {
					onClick(it)
				}
			}
		}
	}
}
@Composable
fun ArticleList(
	articles: LazyPagingItems<Article>,
	onClick: (Article) -> Unit = {}
) {
	val handlePagingResult = HandlePagingResult(articles = articles)
	if(handlePagingResult) {
		LazyColumn(
			modifier = Modifier.fillMaxSize(),
			contentPadding = PaddingValues(all = Dimens.SmallPadding1)
		) {
			items(count = articles.itemCount) {
				articles[it]?.let {
					ArticleCard(article = it) {
						onClick(it)
					}
				}
			}
		}
	}
}

@Composable
fun HandlePagingResult(articles: LazyPagingItems<Article>): Boolean {
	val loadState = articles.loadState
	val error = when {
		loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
		loadState.append is LoadState.Error -> loadState.append as LoadState.Error
		loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
		else -> null
	}

	return when {
		loadState.refresh is LoadState.Loading -> {
			ShowShimmerEffect()
			false
		}
		error != null -> {
			EmptyScreen()
			false
		}
		else -> {
			true
		}
	}
}

@Composable
private fun ShowShimmerEffect() {
	Column {
		repeat(10) {
			ArticleCardShimmer()
		}
	}
}
