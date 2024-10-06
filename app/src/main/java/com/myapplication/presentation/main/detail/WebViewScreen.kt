package com.myapplication.presentation.main.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import android.webkit.WebViewClient
import android.webkit.WebView
import com.myapplication.presentation.main.common.ErrorView

@Composable
fun WebViewScreen(url: String?) {
	if (!url.isNullOrEmpty()) {
		AndroidView(factory = { context ->
			WebView(context).apply {
				webViewClient = WebViewClient() // Prevent links from opening in a browser
				loadUrl(url!!) // Load the initial URL
			}
		}, update = {
			it.loadUrl(url!!)
		})
	} else {
		ErrorView()
	}
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	WebViewScreen(url = "www.google.com")
}