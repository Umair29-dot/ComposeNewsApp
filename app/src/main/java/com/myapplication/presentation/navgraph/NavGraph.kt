package com.myapplication.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.myapplication.presentation.activity.viewmodel.MainActivityViewModel
import com.myapplication.presentation.bottomnav.NewsNavigatorScreen
import com.myapplication.presentation.onboarding.screen.OnBoardingScreen
import com.myapplication.presentation.onboarding.viewmodel.OnBoardingViewModel
import com.myapplication.presentation.splash.SplashScreen

@Composable
fun NavGraph(startDestination: String) {
	val navController = rememberNavController()

	NavHost(navController = navController, startDestination = startDestination) {

		navigation(
			route = Route.AppStartNavigation.route,
			startDestination = Route.SplashScreen.route
		) {
			composable(route = Route.SplashScreen.route) {
				val mainViewModel: MainActivityViewModel = hiltViewModel()
				SplashScreen(navController = navController, mainViewModel)
			}

			composable(route = Route.OnBoardingScreen.route) {
				val viewModel: OnBoardingViewModel = hiltViewModel()
				OnBoardingScreen(viewModel = viewModel, navController = navController)
			}
		}

		navigation(
			route = Route.NewsNavigation.route,
			startDestination = Route.NewsNavigatorScreen.route
		) {
			composable(route = Route.NewsNavigatorScreen.route) {
				NewsNavigatorScreen()
			}
		}
	}
}