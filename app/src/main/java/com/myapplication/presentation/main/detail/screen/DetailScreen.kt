package com.myapplication.presentation.main.detail.screen

import android.content.Context
import android.content.Intent
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
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.presentation.Dimens
import com.myapplication.presentation.main.common.ErrorView
import com.myapplication.presentation.main.common.BackNavigation
import com.myapplication.presentation.main.detail.components.DetailOptions
import com.myapplication.presentation.navgraph.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
	article: Article?,
	navController: NavController
) {
	val context = LocalContext.current
	val modalBottomSheetState = rememberModalBottomSheetState()

	if (article != null) {
		DetailScreenContent(
			article = article,
			navController = navController,
			context = context,
			sheetState = modalBottomSheetState
		)
	} else {
		ErrorView()
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailScreenContent(
	article: Article,
	navController: NavController,
	context: Context,
	sheetState: SheetState
) {
	Box(
		modifier = Modifier
			.fillMaxSize()
	) {
		AsyncImage(
			model = ImageRequest.Builder(context).data(article.urlToImage).build(),
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
						saveArticle(article)
					},
					onShareClick = {
						Intent(Intent.ACTION_SEND).also {
							it.putExtra(Intent.EXTRA_TEXT, article.url ?: "")
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
						text = article?.source?.name ?: "",
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
	}//: Box
}

private fun saveArticle(article: Article) {

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
		navController = rememberNavController()
	)
}