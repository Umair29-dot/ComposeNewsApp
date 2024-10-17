package com.myapplication.presentation.main.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.presentation.Dimens
import com.myapplication.util.extractDate

@Composable
fun SearchListItem(article: Article, onClick: (Article) -> Unit = {}) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.height(360.dp)
		) {
			Card(
				modifier = Modifier
					.fillMaxSize()
					.padding(5.dp),
				elevation = CardDefaults.cardElevation(Dimens.searchCardElevation)
			) {
				Column(
					modifier = Modifier.fillMaxSize()
				) {
					Box(
						modifier = Modifier
							.fillMaxWidth()
							.height(170.dp)
					) {
						AsyncImage(
							model = ImageRequest.Builder(LocalContext.current)
								.data(article.image ?: "")
								.scale(Scale.FILL)
								.build(),
							contentDescription = null,
							contentScale = ContentScale.Crop,
							modifier = Modifier.fillMaxSize()
						)

						Column(modifier = Modifier
							.fillMaxSize()
							.background(
								brush = Brush.verticalGradient(
									colors = listOf(
										Color.Transparent,
										Color.Black.copy(alpha = 0.6f)
									)
								)
							),
							verticalArrangement = Arrangement.Bottom
						) {
							Text(text = article.title ?: "",
								overflow = TextOverflow.Ellipsis,
								maxLines = 2,
								color = Color.White,
								style = MaterialTheme.typography.titleMedium,
								modifier = Modifier.padding(start = 10.dp)
							)

							Spacer(modifier = Modifier.height(20.dp))
						}
					}//: Box

					Spacer(modifier = Modifier.height(10.dp))

					Text(
						extractDate(article.publishedAt ?: ""),
						color = Color.Gray,
						style = MaterialTheme.typography.labelMedium
					)

					if (!article.author.isNullOrEmpty()) {
						Row(verticalAlignment = Alignment.CenterVertically) {
							Icon(
								imageVector = Icons.Default.AddCircle,
								contentDescription = "Bullet Point",
								modifier = Modifier.size(5.dp),
								tint = Color.Gray
							)

							Spacer(modifier = Modifier.width(5.dp))

							Text(article.author ?: "",
								color = Color.Gray,
								style = MaterialTheme.typography.labelMedium,
								maxLines = 1
							)
						}
					}

					Spacer(modifier = Modifier.height(7.dp))

					Text(text = article.description ?: "",
						maxLines = 3,
						overflow = TextOverflow.Ellipsis,
						textAlign = TextAlign.Justify,
						style = MaterialTheme.typography.bodyMedium,
					)

					Spacer(modifier = Modifier.height(5.dp))

					Row(
						modifier = Modifier.fillMaxHeight(),
						verticalAlignment = Alignment.Bottom
					) {
						Button(
							modifier = Modifier.padding(5.dp),
							onClick = {
							onClick(article)
						}) {
							Row {
								Text(text = "Read More",
									modifier = Modifier.fillMaxWidth(),
									textAlign = TextAlign.Center
								)
							}
						}
					}//: Row
				}//: Column
			}//: Card
		}//: Column
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
	SearchListItem(
		article = Article(
			author = "Umair",
			content = "Loperm Ipsum dollar smit",
			description = "Loperm Ipsum dollar smit, Loperm Ipsum dollar smit, Loperm Ipsum dollar smit,",
			publishedAt = "10 Sep 2024",
			source = null,
			title = "Loperm Ipsum dollar smit, Loperm Ipsum dollar smit",
			url = "www.google.com",
			urlToImage = "https://i-invdn-com.investing.com/news/indicatornews_4_800x533_L_1413112066.jpg"
		)
	)
}