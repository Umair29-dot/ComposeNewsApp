package com.myapplication.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapplication.presentation.Dimens

@Composable
fun PageIndicator(
	pageSize: Int,
	selectedPage: Int,
	modifier: Modifier = Modifier,
	horizontal: Arrangement.Horizontal
) {
	Row(
		modifier = modifier,
		horizontalArrangement = horizontal
	) {
		for (i in 0 until pageSize) {
			Box (
				modifier = Modifier
					.padding(3.dp)
					.height(Dimens.IndicatorHeight)
					.width(
						if(i == selectedPage) Dimens.SelectedIndicatorWidth else Dimens.UnSelectedIndicatorWidth
					)
					.clip(CircleShape)
					.background(
						color =  Color.Gray
					)
			)
		}
	}//: Row
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	PageIndicator(
		pageSize = 5,
		selectedPage = 2,
		horizontal = Arrangement.Center
	)
}