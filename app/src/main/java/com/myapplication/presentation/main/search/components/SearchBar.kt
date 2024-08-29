package com.myapplication.presentation.main.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapplication.R

@Composable
fun SearchBar(
	text: String,
	onClick: () -> Unit = {},
	onSearchChange: (String) -> Unit = {}
) {
	BasicTextField(
		value = text,
		onValueChange = {
			onSearchChange(it)
		},
		singleLine = true,
		maxLines = 1,
		cursorBrush = SolidColor(Color.Black),
		textStyle = TextStyle(color = Color.Black),
		keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
		keyboardActions = KeyboardActions(
			onSearch = {
				onClick.invoke()
			}
		),
		modifier = Modifier
			.fillMaxWidth()
			.padding(10.dp)
			.clip(RoundedCornerShape(20.dp))
			.background(color = colorResource(id = R.color.category_background))
			.padding(horizontal = 5.dp, vertical = 12.dp),
		decorationBox = { innerTextField ->
			Row(
				verticalAlignment = Alignment.CenterVertically
			) {
				Icon(
					painter = painterResource(
						id = R.drawable.baseline_search_24
					),
					contentDescription = null,
					tint = Color.Gray.copy(alpha = 0.9f)
				)

				Spacer(modifier = Modifier.width(10.dp))

				if (text.isBlank()) {
					Text(
						text = stringResource(id = R.string.search),
						style = MaterialTheme.typography.labelLarge.copy(color = Color.Gray.copy(alpha = 0.9f))
					)
				}
				innerTextField()
			}
		}
	)
}

@Preview(showBackground = true)
@Composable
private fun Preview(){
	SearchBar(text = "")
}