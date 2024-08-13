package com.myapplication.presentation.onboarding

import androidx.annotation.DrawableRes
import com.myapplication.R

data class Page(
	val title: String,
	val description: String,
	@DrawableRes val image: Int
)

val pages = listOf<Page>(
	Page(
		title = "Lorem Ipsum is simply dummy",
		description = "Lorem ipsum is a dummy text of the printing and industry, Lorem ipsum is a dummy text of the printing and industry",
		image = R.drawable.onboarding1
	),
	Page(
		title = "Lorem Ipsum is simply dummy",
		description = "Lorem ipsum is a dummy text of the printing and industry, Lorem ipsum is a dummy text of the printing and industry",
		image = R.drawable.onboarding2
	),
	Page(
		title = "Lorem Ipsum is simply dummy",
		description = "Lorem ipsum is a dummy text of the printing and industry, Lorem ipsum is a dummy text of the printing and industry",
		image = R.drawable.teacher
	)
)