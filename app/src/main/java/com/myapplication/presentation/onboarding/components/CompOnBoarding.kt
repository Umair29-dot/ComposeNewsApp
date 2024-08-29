package com.myapplication.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapplication.R
import com.myapplication.presentation.Dimens
import com.myapplication.domain.model.onboarding.Page

@Composable
fun CompOnBoarding(
	page: Page
) {
	Column {
		Image(
			painter = painterResource(id = page.image),
			contentDescription = stringResource(R.string.an_image),
			contentScale = ContentScale.Crop,
			modifier = Modifier.height(450.dp)
		)

		Spacer(modifier = Modifier.height(Dimens.MediumPadding2))

		Text(
			text = page.title,
			style = MaterialTheme.typography.headlineMedium,
			modifier = Modifier.padding(horizontal = Dimens.MediumPadding3),
			color = colorResource(id = R.color.black)
		)

		Text(
			text = page.description,
			style = MaterialTheme.typography.bodyMedium,
			modifier = Modifier.padding(horizontal = Dimens.MediumPadding3)
		)
	}//: Column
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	CompOnBoarding(
		page = Page(
			title = "Wellness and Medical News",
			description = "Discover essential health news, from medical breakthroughs to wellness tips, ensuring you stay informed about your health and well-being",
			image = R.drawable.onboarding2
		)
	)
}