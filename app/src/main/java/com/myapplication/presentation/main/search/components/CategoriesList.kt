package com.myapplication.presentation.main.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesList(selectedCategory: (String) -> Unit = {}) {
	val categories = listOf("All", "Business", "Health", "Entertainment", "Science", "Sports", "Technology")
	var selectedIndex = remember {
		mutableStateOf(0)
	}

	LazyRow(
		modifier = Modifier.fillMaxWidth(),
		contentPadding = PaddingValues(5.dp)
	) {
		items(categories.size) {
			CategoriesListItem(
				item = categories[it],
				selectedIndex = selectedIndex.value,
				itemIndex = it
			) { itemIndex, item ->
				selectedIndex.value = itemIndex
				selectedCategory(item)
			}
		}
	}
}

@Composable
fun CategoriesListItem(
	item: String,
	itemIndex: Int,
	selectedIndex: Int,
	onClick: (Int, String) -> Unit
) {
	Text(
		item,
		style = if (itemIndex == selectedIndex) {
			MaterialTheme.typography.bodyMedium.copy(MaterialTheme.colorScheme.primary)
		} else {
			MaterialTheme.typography.bodySmall
		},
		modifier = Modifier
			.padding(horizontal = 8.dp)
			.clickable {
				onClick(itemIndex, item)
			}
	)
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	CategoriesList()
}