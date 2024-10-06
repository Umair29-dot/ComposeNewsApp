package com.myapplication.presentation.main.bookmark

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.myapplication.presentation.Dimens
import com.myapplication.presentation.main.common.ArticleList
import com.myapplication.presentation.main.common.TitleSection
import com.myapplication.presentation.navgraph.Route

@Composable
fun BookmarkScreen(
	viewModel: BookmarkViewModel,
	navController: NavController
) {
	Surface(
		modifier = Modifier
			.fillMaxSize()
	) {

		//val articles = viewModel.articles.collectAsState().value

		Spacer(modifier = Modifier.height(Dimens.LargePadding2))

		TitleSection(
			screenTitle = "Bookmark",
			screenDescription = "Your saved news",
			style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
		)

		Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

		/*ArticleList(
			articles = articles,
			onClick = {
				navController.navigate(Route.DetailScreen.route)
			}
		)*/
	}//: Surface
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
	BookmarkScreen(
		viewModel = hiltViewModel(),
		navController = rememberNavController()
	)
}