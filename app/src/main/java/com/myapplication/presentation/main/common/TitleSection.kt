package com.myapplication.presentation.main.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TitleSection(
	screenTitle: String,
	screenDescription: String?,
	style: TextStyle
) {
	Column(
		modifier = Modifier.fillMaxWidth()
	) {
		Text(
			text = screenTitle,
			style = style
		)

		screenDescription?.let {
			Text(
				text = it,
				style = MaterialTheme.typography.titleSmall
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	TitleSection(
		screenTitle = "Search",
		screenDescription = "News from all around the world",
		style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
	)
}