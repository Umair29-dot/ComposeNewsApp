package com.myapplication.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.airbnb.lottie.BuildConfig
import com.myapplication.presentation.activity.viewmodel.MainActivityViewModel
import com.myapplication.presentation.navgraph.NavGraph
import com.myapplication.presentation.navgraph.Route
import com.myapplication.ui.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		WindowCompat.setDecorFitsSystemWindows(window, false)
		setContent {
			NewsTheme {
				NavGraph(startDestination = Route.AppStartNavigation.route)
			}
		}
	}
}