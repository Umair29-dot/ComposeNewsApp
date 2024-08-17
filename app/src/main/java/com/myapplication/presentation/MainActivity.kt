package com.myapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.myapplication.presentation.navgraph.NavGraph
import com.myapplication.presentation.navgraph.Route
import com.myapplication.ui.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	private val viewModel by viewModels<MainActivityViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		WindowCompat.setDecorFitsSystemWindows(window, false)
		setContent {
			NewsTheme {
				if (viewModel.onBoardingCondition.value) {
					navigate(startDestination = Route.NewsNavigation.route)
				} else {
					navigate(startDestination = Route.AppStartNavigation.route)
				}
			}
		}
	}
}

@Composable
private fun navigate(startDestination: String) {
	NavGraph(startDestination = startDestination)
}