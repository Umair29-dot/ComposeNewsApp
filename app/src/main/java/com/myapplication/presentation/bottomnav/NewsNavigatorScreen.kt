package com.myapplication.presentation.bottomnav

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.myapplication.R
import com.myapplication.presentation.SharedViewModel
import com.myapplication.presentation.main.detail.WebViewScreen
import com.myapplication.presentation.main.bookmark.BookmarkScreen
import com.myapplication.presentation.main.bookmark.BookmarkViewModel
import com.myapplication.presentation.main.detail.DetailScreen
import com.myapplication.presentation.main.detail.DetailViewModel
import com.myapplication.presentation.main.detail.components.TestViewModel
import com.myapplication.presentation.main.home.HomeScreen
import com.myapplication.presentation.main.home.HomeViewModel
import com.myapplication.presentation.main.search.SearchScreen
import com.myapplication.presentation.main.search.SearchViewModel
import com.myapplication.presentation.navgraph.Route

@Composable
fun NewsNavigatorScreen() {

	val sharedViewModel: SharedViewModel = viewModel()
	val snackbarHostState = remember { SnackbarHostState() }
	val bottomNavItems = remember {
		listOf(
			BottomNavigationItem(icon = R.drawable.baseline_home_24, text = "Home"),
			BottomNavigationItem(icon = R.drawable.baseline_search_24, text = "Search"),
			BottomNavigationItem(icon = R.drawable.baseline_bookmark_24, text = "Bookmark")
		)
	}
	val navController = rememberNavController()
	val backStackState = navController.currentBackStackEntryAsState().value
	var selectedItem = rememberSaveable {
		mutableStateOf(0)
	}

	selectedItem.value = when(backStackState?.destination?.route) {
		Route.HomeScreen.route -> 0
		Route.SearchScreen.route -> 1
		Route.BookmarkScreen.route -> 2
		else -> 0
	}

	//To hide the bottom bar when navigate to other screens
	val isBottomBarVisible = remember(backStackState) {
		backStackState?.destination?.route == Route.HomeScreen.route ||
				backStackState?.destination?.route == Route.SearchScreen.route ||
				backStackState?.destination?.route == Route.BookmarkScreen.route
	}

	Scaffold(
		modifier = Modifier
			.fillMaxSize(),
		snackbarHost = { SnackbarHost(snackbarHostState) },
		bottomBar = {
			if (isBottomBarVisible) {
				NewsBottomNavigation(
					items = bottomNavItems,
					selected = selectedItem.value,
					onItemClick = {
						when(it) {
							0 -> navigateToTap(navController, Route.HomeScreen.route)
							1 -> navigateToTap(navController, Route.SearchScreen.route)
							2 -> navigateToTap(navController, Route.BookmarkScreen.route)
						}
					}
				)
			}
		}
	) {
		val bottomPadding = it.calculateBottomPadding()
		NavHost(
			navController = navController,
			startDestination = Route.HomeScreen.route,
			modifier = Modifier.padding(bottom = bottomPadding)
		) {
			composable(route = Route.HomeScreen.route) {
				val viewModel: HomeViewModel = hiltViewModel()
				HomeScreen(viewModel = viewModel, sharedViewModel = sharedViewModel, navController = navController)
			}

			composable(route = Route.SearchScreen.route) {
				val viewModel: SearchViewModel = hiltViewModel()
				SearchScreen(navController = navController, viewModel = viewModel, sharedViewModel = sharedViewModel)
			}

			composable(route = Route.DetailScreen.route) {
				val viewModel: DetailViewModel = hiltViewModel()
				DetailScreen(article = sharedViewModel.article, navController = navController, viewModel = viewModel, snackbarHostState = snackbarHostState)
			}

			composable(route = Route.WebViewScreen.route) {
				WebViewScreen(url = "www.google.com")
			}

			composable(route = Route.BookmarkScreen.route) {
				val viewModel: BookmarkViewModel = hiltViewModel()
				BookmarkScreen(viewModel = viewModel, navController = navController, sharedViewModel = sharedViewModel, snackbarHostState = snackbarHostState)
			}
		}//: NavHost
	}//: Scaffold
}

private fun navigateToTap(
	navController: NavController,
	route: String
) {
	navController.navigate(route) {
		navController.graph.startDestinationRoute?.let {homeScreen ->
			popUpTo(homeScreen) {
				saveState = true
			}
			restoreState = true
			launchSingleTop = true
		}
	}
}