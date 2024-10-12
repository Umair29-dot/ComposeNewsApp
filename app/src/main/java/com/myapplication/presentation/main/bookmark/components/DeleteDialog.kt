package com.myapplication.presentation.main.bookmark.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun DeleteDialog(
	onDismiss: () -> Unit = {},
	onDelete: () -> Unit = {}
) {
	Dialog(
		onDismissRequest = {
			onDismiss.invoke()
		},
		properties = DialogProperties(
			dismissOnBackPress = false,dismissOnClickOutside = false
		)
	) {
		Card(
			shape = RoundedCornerShape(10.dp),
			modifier = Modifier
				.fillMaxWidth()
				.height(200.dp),
			elevation = CardDefaults.cardElevation(5.dp)
		) {
			Column(
				modifier = Modifier
					.fillMaxSize(),
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally
			) {

				Icon(
					imageVector = Icons.Filled.Delete,
					contentDescription = "Delete Icon",
					modifier = Modifier
						.size(30.dp),
					tint = MaterialTheme.colorScheme.error
				)

				Spacer(modifier = Modifier.height(20.dp))

				Text("Are you sure to delete this Article?",
					textAlign = TextAlign.Center)

				Spacer(modifier = Modifier.height(10.dp))

				Row {
					Button(
						onClick = {
							onDismiss.invoke()
						},
						colors = ButtonDefaults.buttonColors(Color.Gray)
					) {
						Text("Cancel")
					}

					Spacer(modifier = Modifier.width(20.dp))

					Button(
						onClick = {
							onDelete.invoke()
						},
						colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
					) {
						Text("DELETE")
					}
				}

			}//: Column
		}//: Card
	}
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	DeleteDialog()
}