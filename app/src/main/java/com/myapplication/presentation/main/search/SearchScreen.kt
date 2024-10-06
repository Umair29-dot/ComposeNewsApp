package com.myapplication.presentation.main.search

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.presentation.Dimens
import com.myapplication.presentation.SharedViewModel
import com.myapplication.presentation.main.common.ArticleCard
import com.myapplication.presentation.main.common.ArticleList
import com.myapplication.presentation.main.common.TitleSection
import com.myapplication.presentation.main.search.components.CategoriesList
import com.myapplication.presentation.main.search.components.SearchBar
import com.myapplication.presentation.main.search.components.SearchListItem
import com.myapplication.presentation.navgraph.Route

@Composable
fun SearchScreen(
	navController: NavController,
	viewModel: SearchViewModel,
	sharedViewModel: SharedViewModel
) {
	val context = LocalContext.current
	val articles: LazyPagingItems<Article> = viewModel.search.collectAsLazyPagingItems()
	var searchValue = remember {
		mutableStateOf("")
	}
	val density = LocalDensity.current

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(all = 10.dp),
	) {

		Spacer(modifier = Modifier.height(Dimens.LargePadding3))

		TitleSection(
			screenTitle = "Search",
			screenDescription = "News from all around the world",
			style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
		)

		Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

		SearchBar(
			text = searchValue.value,
			onSearchChange = {
				searchValue.value = it
			},
			onClick = {
				if(!searchValue.value.trim().isNullOrEmpty()) {
					viewModel.searchNews(searchQuery = searchValue.value.trim())
				} else {
					Toast.makeText(context, "Field can't be empty", Toast.LENGTH_SHORT).show()
				}
			}
		)

		Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

		CategoriesList()

		Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

		LazyColumn(
			modifier = Modifier.fillMaxSize(),
			contentPadding = PaddingValues(all = Dimens.SmallPadding1),
		) {
			items(count = articles.itemCount) {
				articles[it]?.let {
					SearchListItem(article = it)
				}
			}
		}
		/*ArticleList(
			articles = articles,
			onClick = {
				sharedViewModel.setArticle(it)
				navController.navigate(Route.DetailScreen.route)
			}
		)*/
	}//: Column
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	SearchScreen(
		navController = rememberNavController(),
		viewModel = hiltViewModel(),
		sharedViewModel = viewModel()
	)
}