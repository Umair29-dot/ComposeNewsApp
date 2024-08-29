package com.myapplication.presentation.bottomnav

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapplication.R

@Composable
fun NewsBottomNavigation(
	items: List<BottomNavigationItem>,
	selected: Int,
	onItemClick: (Int) -> Unit = {}
) {
	NavigationBar(
		modifier = Modifier
			.fillMaxWidth(),
		tonalElevation = 10.dp
	) {
		items.forEachIndexed { index, item ->
			NavigationBarItem(
				selected = selected == index,
				onClick = {
					onItemClick(index)
				},
				icon = {
					Icon(
						painter = painterResource(id = item.icon),
						contentDescription = null
					)
				},
				label = {
					Text(
						text = item.text,
						style = MaterialTheme.typography.labelSmall
					)
				}
			)
		}
	}
}

data class BottomNavigationItem(
	val icon: Int,
	val text: String
)

@Composable
@Preview(showBackground = true)
private fun Preview() {
	val navItems = listOf(
		BottomNavigationItem(icon = R.drawable.baseline_home_24, text = "Home"),
		BottomNavigationItem(icon = R.drawable.baseline_search_24, text = "Search"),
		BottomNavigationItem(icon = R.drawable.baseline_bookmark_24, text = "Bookmark")
	)

	NewsBottomNavigation(
		items = navItems,
		selected = 0
	)
}