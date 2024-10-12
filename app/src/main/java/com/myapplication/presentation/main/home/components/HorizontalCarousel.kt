package com.myapplication.presentation.main.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.myapplication.domain.model.news.gnews.GArticle
import com.myapplication.domain.model.onboarding.pages

@Composable
fun HorizontalCarousel(
	gArticles: List<GArticle>,
	pagerState: PagerState,
	onClick: (GArticle) -> Unit
) {

	HorizontalPager(
		modifier = Modifier
			.fillMaxWidth()
			.height(250.dp),
		state = pagerState
	) {
		val article = gArticles[it]

		Card(
			modifier = Modifier
				.fillMaxWidth()
				.height(230.dp)
				.clickable {
						   onClick(article)
				},
			shape = RoundedCornerShape(corner = CornerSize(15.dp))
		) {
			Box(contentAlignment = Alignment.BottomStart) {
				AsyncImage(
					model = ImageRequest.Builder(LocalContext.current)
						.data(article.image ?: "")
						.scale(Scale.FILL)
						.build(),
					contentDescription = null,
					contentScale = ContentScale.Crop,
					modifier = Modifier.fillMaxSize()
				)

				Box(
					modifier = Modifier
						.fillMaxSize()
						.background(
							brush = Brush.verticalGradient(
								colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))
							)
						)
				)

				Text(
					text = article.title ?: "",
					color = Color.White,
					maxLines = 2,
					modifier = Modifier
						.align(Alignment.BottomStart)
						.padding(16.dp),
					style = MaterialTheme.typography.titleMedium
				)
			}//: Box
		}//: Card
	}

}