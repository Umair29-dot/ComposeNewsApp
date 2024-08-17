package com.myapplication.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.myapplication.domain.nmodel.Article
import com.myapplication.domain.nmodel.Source
import com.myapplication.presentation.Dimens

@Composable
fun ArticleCard(
	article: Article,
	onClick: () -> Unit = {}
) {
	val context = LocalContext.current

	Row(
		modifier = Modifier.fillMaxWidth()
			.clickable {
					  onClick()
			},
	) {
		AsyncImage(
			model = ImageRequest.Builder(context).data(article.urlToImage).build(),
			contentDescription = "News Image",
			modifier = Modifier
				.size(Dimens.ArticleImage)
				.clip(
					RoundedCornerShape(
						topStart = 10.dp,
						topEnd = 10.dp,
						bottomStart = 10.dp,
						bottomEnd = 10.dp
					)
				)
		)

		Column(
			modifier = Modifier.padding(Dimens.smallPadding1)
		) {
			Text(
				text = article.title ?: "",
			    maxLines = 2,
			    overflow = TextOverflow.Ellipsis,
			    style = MaterialTheme.typography.bodyMedium
			)

			Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

			Row {
				Text(
					article.source?.name ?: "",
				     style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
				     modifier = Modifier.weight(1f)
				)

				Text(
					article.publishedAt ?: "",
				         modifier = Modifier.weight(3f)
				)
			}//: Row
		}//: Column
	}//: Row
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	ArticleCard(
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