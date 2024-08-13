package com.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.myapplication.domain.usecases.AppEntryUseCases
import com.myapplication.presentation.onboarding.screens.OnBoardingScreen
import com.myapplication.ui.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	@Inject
	lateinit var appEntryUseCases: AppEntryUseCases

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		WindowCompat.setDecorFitsSystemWindows(window,  false)

		lifecycleScope.launch {
			appEntryUseCases.readAppEntry().collect {
				Log.d("Test", it.toString())
			}
		}

		setContent {
			NewsTheme {
				OnBoardingScreen()
			}
		}
	}
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	NewsTheme {
		OnBoardingScreen()
	}
}