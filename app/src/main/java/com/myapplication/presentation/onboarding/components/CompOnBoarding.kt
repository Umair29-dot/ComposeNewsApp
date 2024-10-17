package com.myapplication.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapplication.R
import com.myapplication.presentation.Dimens
import com.myapplication.domain.model.onboarding.Page

@Composable
fun CompOnBoarding(
	page: Page
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Image(
			painter = painterResource(id = page.image),
			contentDescription = stringResource(R.string.an_image),
			contentScale = ContentScale.Crop,
			modifier = Modifier.height(400.dp)
		)

		Spacer(modifier = Modifier.height(Dimens.MediumPadding2))

		Text(
			modifier = Modifier
				.padding(vertical = Dimens.MediumPadding3),
			text = page.title,
			fontFamily = FontFamily(Font(R.font.montserrat_medium)),
			fontWeight = FontWeight.Bold,
			fontSize = 30.sp,
			color = colorResource(id = R.color.black)
		)

		Text(
			text = page.description,
			textAlign = TextAlign.Justify,
			style = MaterialTheme.typography.bodyMedium,
			modifier = Modifier.width(300.dp)
		)
	}//: Column
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	CompOnBoarding(
		page = Page(
			title = "Health News",
			description = "Discover essential health news, from medical breakthroughs to wellness tips, ensuring you stay informed about your health and well-being",
			image = R.drawable.onboarding2
		)
	)
}