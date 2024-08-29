package com.myapplication.presentation.main.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun BackNavigation(
	modifier: Modifier = Modifier,
	onBackClick: () -> Unit = {}
) {

	Column {
		Spacer(modifier = modifier)

		IconButton(
			onClick = {
				onBackClick.invoke()
			},
			modifier = Modifier
				.padding(10.dp)
				.clip(CircleShape)
				.background(color = colorResource(id = R.color.icon_background))
				.size(40.dp)
		) {
			Icon(
				painter = painterResource(id = R.drawable.baseline_arrow_back_24),
				contentDescription = null,
				modifier = Modifier.size(25.dp)
			)
		}
	}

	/*TopAppBar(
		modifier = Modifier.fillMaxWidth(),
		title = {},
		navigationIcon = {
			IconButton(onClick = {
				onBackClick.invoke()
			}) {
				Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = null)
			}
		},
		actions = {
			IconButton(onClick = {
				onBookmarkClick.invoke()
			}) {
				Icon(painter = painterResource(id = R.drawable.baseline_bookmark_24), contentDescription = null)
			}

			IconButton(onClick = {
				onShareClick.invoke()
			}) {
				Icon(painter = painterResource(id = R.drawable.baseline_share_24), contentDescription = null)
			}

			IconButton(onClick = {
				onBrowsingClick.invoke()
			}) {
				Icon(painter = painterResource(id = R.drawable.baseline_language_24), contentDescription = null)  //used for webview
			}
		}
	)*/
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
	BackNavigation()
}