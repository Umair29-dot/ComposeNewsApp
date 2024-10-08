package com.myapplication.presentation.main.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapplication.R

@Composable
fun CategoriesList() {
	val categories = listOf("All", "Business", "Health", "Entertainment", "Science", "Sports", "Technology")

	LazyRow(
		modifier = Modifier.fillMaxWidth()
	) {
		items(categories) {
			CategoryItem(item = it)
		}
	}
}

@Composable
fun CategoryItem(item: String) {
	Box(
		modifier = Modifier
			.padding(all = 7.dp)
			.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
			.background(color = colorResource(id = R.color.category_background))
			.padding(vertical = 5.dp, horizontal = 10.dp)
	) {
		Text(
			item,
		    modifier = Modifier
				.align(Alignment.Center),
			style = MaterialTheme.typography.labelMedium
		)
	}
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	CategoriesList()
}