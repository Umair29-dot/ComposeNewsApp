package com.myapplication.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.myapplication.R

val montserrat = FontFamily(
	Font(R.font.montserrat_regular, FontWeight.Normal),
	Font(R.font.montserrat_medium, FontWeight.Medium),
	Font(R.font.montserrat_bold, FontWeight.Bold)
)

val news = FontFamily(
	Font(R.font.news_regular, FontWeight.Normal),
	Font(R.font.news_medium, FontWeight.Medium),
	Font(R.font.news_bold, FontWeight.Bold)
)


val Typography = Typography(
	headlineLarge = TextStyle(
		fontFamily = montserrat,
		fontWeight = FontWeight.Bold,
		fontSize = 40.sp
	),
	headlineMedium = TextStyle(
		fontFamily = montserrat,
		fontWeight = FontWeight.Medium,
		fontSize = 25.sp
	),
	headlineSmall = TextStyle(
		fontFamily = montserrat,
		fontWeight = FontWeight.Medium,
		fontSize = 18.sp
	),
	titleLarge = TextStyle(
		fontFamily = news,
		fontWeight = FontWeight.Bold,
		fontSize = 25.sp
	),
	titleMedium = TextStyle(
		fontFamily = news,
		fontWeight = FontWeight.Medium,
		fontSize = 19.sp
	),
	titleSmall = TextStyle(
		fontFamily = montserrat,
		fontWeight = FontWeight.Normal,
		fontSize = 15.sp
	),
	bodyLarge = TextStyle(
		fontFamily = news,
		fontWeight = FontWeight.Normal,
		fontSize = 25.sp
	),
	bodyMedium = TextStyle(
		fontFamily = news,
		fontWeight = FontWeight.Normal,
		fontSize = 18.sp
	),
	bodySmall = TextStyle(
		fontFamily = news,
		fontWeight = FontWeight.Normal,
		fontSize = 14.sp
	)

)