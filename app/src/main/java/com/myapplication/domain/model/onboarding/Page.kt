package com.myapplication.domain.model.onboarding

import androidx.annotation.DrawableRes
import com.myapplication.R

data class Page(
	val title: String,
	val description: String,
	@DrawableRes val image: Int
)

val pages = listOf<Page>(
	Page(
		title = "Global Conflict Updates",
		description = "Stay updated on the latest developments in global conflicts, with in-depth analysis and real-time news from the world's most pressing hotspots",
		image = R.drawable.onboarding1
	),
	Page(
		title = "Wellness and Medical",
		description = "Discover essential health news, from medical breakthroughs to wellness tips, ensuring you stay informed about your health and well-being",
		image = R.drawable.onboarding2
	),
	Page(
		title = "Innovations and Trends",
		description = "Explore the latest in technology, from groundbreaking innovations to future trends, keeping you informed about the ever-evolving digital landscape",
		image = R.drawable.onboarding3
	)
)