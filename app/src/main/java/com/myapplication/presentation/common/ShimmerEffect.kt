package com.myapplication.presentation.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapplication.R
import com.myapplication.domain.nmodel.Article
import com.myapplication.presentation.Dimens

fun Modifier.shimmerEffect() = composed {
	val transition = rememberInfiniteTransition()
	val alpha = transition.animateFloat(
		initialValue = 0.2f,
		targetValue = 0.9f,
		animationSpec = infiniteRepeatable(
			animation = tween(durationMillis = 1000),
			repeatMode = RepeatMode.Reverse
		)
	).value
	background(color = colorResource(id = R.color.shimmer))
}

@Composable
fun ArticleCardShimmer() {
	Row(
		modifier = Modifier.fillMaxWidth()
			.padding(Dimens.MediumPadding1)
	) {
		Box(
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
				.shimmerEffect()
		)

		Column(
			modifier = Modifier.padding(Dimens.smallPadding1)
		) {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.height(30.dp)
					.shimmerEffect()
			)

			Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

			Row {
				Box(
					modifier = Modifier
						.fillMaxWidth(0.5f)
						.height(20.dp)
						.shimmerEffect()
				)
			}//: Row
		}//: Column
	}//: Row
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	ArticleCardShimmer()
}