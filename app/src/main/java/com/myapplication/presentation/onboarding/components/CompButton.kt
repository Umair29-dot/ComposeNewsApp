package com.myapplication.presentation.onboarding.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CompButton(
	title: String,
	onClick: () -> Unit = {}
) {
	Button(
		modifier = Modifier.clip(RoundedCornerShape(size = 5.dp)),
		onClick = {
		onClick.invoke()
	}) {
		Text(text = title)
	}
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	CompButton(title = "Click Me")
}