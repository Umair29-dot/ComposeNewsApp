package com.myapplication.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.myapplication.R
import com.myapplication.presentation.Dimens
import com.myapplication.presentation.SharedViewModel
import com.myapplication.presentation.activity.viewmodel.MainActivityViewModel
import com.myapplication.presentation.navgraph.NavGraph
import com.myapplication.presentation.navgraph.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@Composable
fun SplashScreen(navController: NavController, viewModel: MainActivityViewModel) {
	var showTextSection = remember { mutableStateOf(false) }

	val lottieComposition by rememberLottieComposition(
		LottieCompositionSpec.RawRes(
			R.raw.news_animation
		)
	)

	val progress by animateLottieCompositionAsState(
		lottieComposition,
		iterations = LottieConstants.IterateForever,
		isPlaying = true
	)

	var navigateStatus = remember { mutableStateOf(false) }

	Surface(
		modifier = Modifier
			.fillMaxSize(),
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(
					color = Color.Black
				),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			LaunchedEffect(key1 = null) {
				delay(1500)
				showTextSection.value = true
			}

			lottieComposition?.let {
				LottieSection(lottieComposition = it, progress = progress)
			}

			if (showTextSection.value) {
				TextSection(appName = stringResource(id = R.string.app_name)) {
					navigateStatus.value = it
				}
			}

			if (navigateStatus.value) {
				navigate(viewModel = viewModel, navController = navController)
			}
		}//: Column
	}//: Column
}

@Composable
private fun LottieSection(
	lottieComposition: LottieComposition,
	progress: Float
) {

	LottieAnimation(
		composition = lottieComposition,
		progress = progress,
		modifier = Modifier
			.size(Dimens.lottieSize)
	)
}

@Composable
private fun TextSection(appName: String, onComplete: (Boolean) -> Unit) {
	var name = remember { mutableStateOf("") }

	LaunchedEffect(key1 = null) {
		for (char in appName) {
			name.value = name.value + char
			delay(200)
		}

		onComplete(true)
	}

	Text(
		name.value,
		style = MaterialTheme.typography.headlineLarge.copy(color = Color.White)
	)
}

@Composable
private fun navigate(
	viewModel: MainActivityViewModel,
	navController: NavController
) {
	if (viewModel.onBoardingCondition.value) {
		navController.navigate(Route.NewsNavigatorScreen.route) {
			popUpTo(Route.AppStartNavigation.route) {
				inclusive = true
			}
		}
	} else {
		navController.navigate(Route.OnBoardingScreen.route)
	}
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
	SplashScreen(
		navController = rememberNavController(),
		viewModel = hiltViewModel()
	)
}