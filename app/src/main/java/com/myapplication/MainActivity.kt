package com.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.myapplication.data.manager.LocalUserManagerImplementation
import com.myapplication.domain.manager.LocalUserManager
import com.myapplication.domain.usecases.AppEntryUseCases
import com.myapplication.presentation.navgraph.NavGraph
import com.myapplication.presentation.navgraph.Route
import com.myapplication.presentation.onboarding.screens.OnBoardingScreen
import com.myapplication.ui.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	private val viewModel by viewModels<MainActivityViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		WindowCompat.setDecorFitsSystemWindows(window,  false)
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
