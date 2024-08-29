package com.myapplication.presentation.main.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapplication.R

@Composable
fun DetailOptions(
	onBrowsingClick: () -> Unit = {},
	onShareClick: () -> Unit = {},
	onBookmarkClick: () -> Unit = {},
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(10.dp),
		horizontalArrangement = Arrangement.End
	) {
		IconButton(
			onClick = {
				onBookmarkClick.invoke()
			},
			modifier = Modifier
				.padding(5.dp)
				.clip(CircleShape)
				.background(color = colorResource(id = R.color.icon_background))
				.size(30.dp)
		) {
			Icon(
				painter = painterResource(id = R.drawable.baseline_bookmark_24),
				contentDescription = null,
				modifier = Modifier.size(20.dp)
			)
		}

		Spacer(modifier = Modifier.width(5.dp))

		IconButton(
			onClick = {
				onShareClick.invoke()
			},
			modifier = Modifier
				.padding(5.dp)
				.clip(CircleShape)
				.background(color = colorResource(id = R.color.icon_background))
				.size(30.dp)
		) {
			Icon(
				painter = painterResource(id = R.drawable.baseline_share_24),
				contentDescription = null,
				modifier = Modifier.size(20.dp)
			)
		}

		Spacer(modifier = Modifier.width(5.dp))

		IconButton(
			onClick = {
				onBrowsingClick.invoke()
			},
			modifier = Modifier
				.padding(5.dp)
				.clip(CircleShape)
				.background(color = colorResource(id = R.color.icon_background))
				.size(30.dp)
		) {
			Icon(
				painter = painterResource(id = R.drawable.baseline_language_24),
				contentDescription = null,
				modifier = Modifier.size(20.dp)
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	DetailOptions()
}