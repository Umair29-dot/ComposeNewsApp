package com.myapplication.presentation.main.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.myapplication.domain.model.news.newsapi.Article
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.myapplication.domain.model.news.gnews.GArticle
import com.myapplication.presentation.Dimens
import com.myapplication.presentation.SharedViewModel
import com.myapplication.presentation.main.common.ArticleList
import com.myapplication.presentation.main.common.TitleSection
import com.myapplication.presentation.main.home.components.HorizontalCarousel
import com.myapplication.presentation.main.home.viewmodel.HomeViewModel
import com.myapplication.presentation.navgraph.Route
import com.myapplication.presentation.onboarding.components.PageIndicator

@Composable
fun HomeScreen(
	viewModel: HomeViewModel,
	sharedViewModel: SharedViewModel,
	navController: NavController
) {
	val articles: LazyPagingItems<Article> = viewModel.news.collectAsLazyPagingItems()
	val gArticles: List<GArticle> = viewModel.gNews.collectAsStateWithLifecycle().value

	val pagerState = rememberPagerState(initialPage = 0) {
		gArticles.size
	}

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(all = 10.dp),
	) {

		Spacer(modifier = Modifier.height(Dimens.LargePadding3))

		TitleSection(
			screenTitle = "Breaking News",
			screenDescription = null,
			style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
		)

		Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

		HorizontalCarousel(gArticles = gArticles, pagerState)

		PageIndicator(
			pageSize = pagerState.pageCount,
			selectedPage = pagerState.currentPage,
			horizontal = Arrangement.Center,
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

		TitleSection(
			screenTitle = "Recommended for you",
			screenDescription = null,
			style = MaterialTheme
				.typography.headlineSmall
				.copy(fontWeight = FontWeight.Bold, color = Color.Blue.copy(alpha = 0.7f))
		)

		Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

		ArticleList(
			articles = articles,
			onClick = {
				sharedViewModel.setArticle(it)
				navController.navigate(Route.DetailScreen.route)
			}
		)
	}//: Column
}

@Preview
@Composable
private fun Preview() {
	HomeScreen(viewModel = hiltViewModel(),
		sharedViewModel = hiltViewModel(),
		navController = rememberNavController()
	)
}