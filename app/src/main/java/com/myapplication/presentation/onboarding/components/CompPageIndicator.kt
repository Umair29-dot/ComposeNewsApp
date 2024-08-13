package com.myapplication.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapplication.presentation.Dimens

@Composable
fun CompPageIndicator(
	pageSize: Int,
	selectedPage: Int,
	selectedColor: Color = MaterialTheme.colorScheme.primary,
	unSelectedColor: Color = Color.Blue
) {
	Row() {
		for (i in 0 until pageSize) {
			Box (
				modifier = Modifier
					.padding(4.dp)
					.size(Dimens.IndicatorSize)
					.clip(CircleShape)
					.background(
						color = if (i == selectedPage) Color.Blue else Color.Gray
					)
			)
		}
	}//: Row
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	CompPageIndicator(pageSize = 5, selectedPage = 2)
}