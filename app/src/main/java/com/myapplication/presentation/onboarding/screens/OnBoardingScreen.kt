package com.myapplication.presentation.onboarding.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapplication.presentation.Dimens
import com.myapplication.presentation.onboarding.components.CompButton
import com.myapplication.presentation.onboarding.components.CompOnBoarding
import com.myapplication.presentation.onboarding.components.CompPageIndicator
import com.myapplication.presentation.onboarding.pages
import com.myapplication.presentation.onboarding.viewmodel.OnBoardingViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(viewModel: OnBoardingViewModel) {

	val pages = pages
	val pagerState = rememberPagerState(initialPage = 0) {
		pages.size
	}
	val buttonState = remember {
		derivedStateOf {
			when(pagerState.currentPage) {
				0 -> listOf("", "Next")
			    1 -> listOf("Back", "Next")
				2 -> listOf("Back", "Get Started")
				else -> listOf("", "")
			}
		}
	}

	Surface(
		modifier = Modifier
			.fillMaxSize()
	) {
		Column {
			HorizontalPager(state = pagerState) {
				CompOnBoarding(page = pages[it])
			}

			Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

			Row(
				modifier = Modifier
					.padding(horizontal = Dimens.MediumPadding3)
					.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				CompPageIndicator(pageSize = pages.size, selectedPage = pagerState.currentPage)

				Row(
					verticalAlignment = Alignment.CenterVertically
				) {
					val scope = rememberCoroutineScope()

					if (buttonState.value[0] != "") {
						BackButton(title = buttonState.value[0]) {
							if(pagerState.currentPage > 0) {
								scope.launch {
									pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
								}
							}
						}

						Spacer(modifier = Modifier.width(5.dp))
					}

					CompButton(title = buttonState.value[1]) {
						if (pagerState.currentPage == pages.size - 1) {
							viewModel.saveAppEntry()
						} else {
							scope.launch {
								pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
							}
						}
					}
				}//: Row
			}//: Row
		}//: Surface
	}//: Surface
}

@Composable
private fun BackButton(title: String, onClick: () -> Unit = {}) {
	Text(
		text = title,
		color = Color.Gray,
		modifier = Modifier
			.clickable {
				onClick.invoke()
			},
		style = MaterialTheme.typography.titleMedium
	)
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	OnBoardingScreen(viewModel = hiltViewModel())
}