package com.myapplication.domain.model.onboarding

import androidx.annotation.DrawableRes
import com.myapplication.R

data class Page(
	val title: String,
	val description: String,
	@DrawableRes val image: Int
)

val pages = listOf(
	Page(
		title = "Conflict Updates",
		description = "Stay updated on the latest developments in global conflicts, with in-depth analysis from the world's most pressing hotspots",
		image = R.drawable.onboarding1
	),
	Page(
		title = "Health Updates",
		description = "Discover essential health news, from medical breakthroughs to wellness tips, ensuring you stay informed about your health",
		image = R.drawable.onboarding2
	),
	Page(
		title = "Technology Updates",
		description = "Explore the latest in technology, from groundbreaking innovations to future trends, keeping you informed about the digital landscape",
		image = R.drawable.onboarding3
	)
)