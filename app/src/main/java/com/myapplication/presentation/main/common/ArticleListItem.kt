package com.myapplication.presentation.main.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.myapplication.R
import com.myapplication.domain.model.news.newsapi.Article
import com.myapplication.domain.model.news.newsapi.Source
import com.myapplication.presentation.Dimens
import com.myapplication.util.extractDate

@Composable
fun ArticleListItem(
	article: Article,
	onClick: () -> Unit = {}
) {
	val context = LocalContext.current

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 5.dp)
			.border(1.dp, Color.Gray, RoundedCornerShape(8.dp))

	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(all = 5.dp)
				.clickable {
					onClick()
				},
		) {
			AsyncImage(
				model = ImageRequest.Builder(context).data(article.urlToImage).build(),
				contentDescription = null,
				modifier = Modifier
					.size(Dimens.ArticleImage)
					.clip(
						RoundedCornerShape(10.dp)
					),
				contentScale = ContentScale.Crop
			)

			Column(
				modifier = Modifier.padding(Dimens.SmallPadding1)
			) {
				Text(
					text = article.title ?: "",
					maxLines = 3,
					minLines = 3,
					overflow = TextOverflow.Ellipsis,
					style = MaterialTheme.typography.titleMedium
				)

				Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

				Row(
					modifier = Modifier.fillMaxWidth(),
					verticalAlignment = Alignment.CenterVertically
				) {
					Text(
						article.source?.name ?: "",
						style = MaterialTheme.typography.labelMedium,
						modifier = Modifier.weight(3f)
					)

					article.publishedAt?.let {
						Row(
							modifier = Modifier.weight(3f),
							verticalAlignment = Alignment.CenterVertically
						) {
							Icon(
								painter = painterResource(id = R.drawable.clock),
								contentDescription = null,
								modifier = Modifier
									.size(13.dp)
							)

							Spacer(modifier = Modifier.width(3.dp))

							Text(
								extractDate(it),
								style = MaterialTheme.typography.labelMedium
							)
						}
					}
				}//: Row
			}//: Column
		}//: Row
	}//: Column
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	ArticleListItem(
		article = Article(
			author = "Umair",
			content = "Hello World",
			description = "Loperm Ipsum seun ahmetnt alidndbf shjdfufm swujiei",
			publishedAt = "09/1/2023",
			title = "A hunger World Loperm Ipsum seun ahmetnt alidndbf",
			url = "https://biztoc.com/x/2394af9de19aec9c",
			urlToImage = "https://biztoc.com/cdn/800/og.png",
			source = Source(
				id = null,
				name = "BBC"
			)
		)
	)
}