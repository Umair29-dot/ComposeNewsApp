package com.myapplication.presentation.main.WebView

import android.graphics.Bitmap
import android.webkit.WebResourceRequest
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import android.webkit.WebViewClient
import android.webkit.WebView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.myapplication.presentation.main.common.ErrorView

@Composable
fun WebViewScreen(url: String?) {
	var isLoading = remember { mutableStateOf(true) }

	if (!url.isNullOrEmpty()) {
		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			AndroidView(factory = { context ->
				WebView(context).apply {
					webViewClient = object : WebViewClient() { // Prevent links from opening in a browser and also give more control over android webview//
						override fun onPageStarted(
							view: WebView?,
							url: String?,
							favicon: Bitmap?
						) {
							super.onPageStarted(view, url, favicon)
							isLoading.value = true
						}

						override fun onPageFinished(view: WebView?, url: String?) {
							super.onPageFinished(view, url)
							isLoading.value = false
						}

						override fun shouldOverrideUrlLoading(
							view: WebView?,
							request: WebResourceRequest?
						): Boolean {
							return false
						}
					}
					loadUrl(url!!) // Load the initial URL
				}
			},
				update = {
				it.loadUrl(url!!)
			},
				modifier = Modifier.fillMaxSize()
			)

			if (isLoading.value) {
				CircularProgressIndicator()
			}
		}//: Box
	} else {
		ErrorView()
	}
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
	WebViewScreen(url = "www.google.com")
}