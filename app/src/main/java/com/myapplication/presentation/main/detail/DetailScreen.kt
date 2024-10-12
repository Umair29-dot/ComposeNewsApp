package com.myapplication.presentation.main.detail

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.myapplication.domain.model.news.CommonArticle
import com.myapplication.domain.model.news.gnews.GArticle
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.presentation.Dimens
import com.myapplication.presentation.main.common.ErrorView
import com.myapplication.presentation.main.common.BackNavigation
import com.myapplication.presentation.main.detail.components.DetailOptions
import com.myapplication.presentation.main.search.SearchViewModel
import com.myapplication.presentation.navgraph.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
	article: CommonArticle?,
	navController: NavController,
	viewModel: DetailViewModel,
	snackbarHostState: SnackbarHostState
) {
	val context = LocalContext.current

	if (article != null) {
		DetailScreenContent(
			article = article,
			navController = navController,
			context = context,
			viewModel = viewModel,
			snackbarHostState =  snackbarHostState
		)
	} else {
		ErrorView()
	}
}

@Composable
private fun DetailScreenContent(
	article: CommonArticle,
	navController: NavController,
	context: Context,
	viewModel: DetailViewModel,
	snackbarHostState:  SnackbarHostState
) {
	Box(
		modifier = Modifier
			.fillMaxSize()
	) {
		AsyncImage(
			model = ImageRequest.Builder(context).data(article.image ?: "").build(),
			contentDescription = null,
			modifier = Modifier
				.fillMaxWidth()
				.height(Dimens.ArticleImageHeight),
			contentScale = ContentScale.Crop
		)

		BackNavigation(
			modifier = Modifier.height(30.dp),
			onBackClick = {
				navController.navigateUp()
			}
		)

		Column {
			Spacer(
				modifier = Modifier
					.height(Dimens.ArticleImageHeight - 50.dp)
			)

			Card(
				modifier = Modifier
					.fillMaxSize(),
				shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
				elevation = CardDefaults.cardElevation(10.dp),
				colors = CardDefaults.cardColors(containerColor = Color.White)
			) {
				Spacer(modifier = Modifier.height(5.dp))

				DetailOptions(
					onBrowsingClick = {
						navController.navigate(Route.WebViewScreen.route)
					},
					onBookmarkClick = {
						viewModel.saveArticle(article)
					},
					onShareClick = {
						Intent(Intent.ACTION_SEND).also {
							it.putExtra(Intent.EXTRA_TEXT, article.url)
							it.type = "text/plain"
							if (it.resolveActivity(context.packageManager) != null) {
								context.startActivity(it)
							}
						}
					}
				)

				Card(
					elevation = CardDefaults.cardElevation(3.dp),
					colors = CardDefaults.cardColors(containerColor = Color.Black),
					modifier = Modifier.padding(all = 10.dp)
				) {
					Text(
						text = article?.getArticleSource()?.name ?: "",
						color = Color.White,
						modifier = Modifier.padding(8.dp)
					)
				}

				Spacer(
					modifier = Modifier
						.height(Dimens.MediumPadding1)
				)

				Text(
					text = article.title ?: "",
					style = MaterialTheme.typography.titleLarge,
					modifier = Modifier.padding(all = 10.dp)
				)

				Spacer(modifier = Modifier.height(Dimens.SmallPadding1))

				Text(
					text = article.description ?: "",
					textAlign = TextAlign.Justify,
					style = MaterialTheme.typography.bodyMedium,
					modifier = Modifier
						.padding(all = 10.dp)
						.fillMaxWidth(),
					overflow = TextOverflow.Ellipsis
				)
			}//: Card
		}

		LaunchedEffect(true) {
			viewModel.result.collectLatest {
				if (it != -1L) {
					snackbarHostState.showSnackbar(message = "Article Saved")
				}
			}
		}
	}//: Box
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	DetailScreen(
		article = Article(
			author = "Umair",
			content = "Loperm Ipsum dollar smit",
			description = "Loperm Ipsum dollar smit, Loperm Ipsum dollar smit, Loperm Ipsum dollar smit,",
			publishedAt = null,
			source = null,
			title = "Loperm Ipsum dollar smit, Loperm Ipsum dollar smit",
			url = "www.google.com",
			urlToImage = "https://i-invdn-com.investing.com/news/indicatornews_4_800x533_L_1413112066.jpg"
		),
		navController = rememberNavController(),
		viewModel = hiltViewModel(),
		snackbarHostState = SnackbarHostState()
	)
}