package com.myapplication.presentation.main.bookmark

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.myapplication.R
import com.myapplication.domain.model.news.CommonArticle
import com.myapplication.presentation.Dimens
import com.myapplication.presentation.SharedViewModel
import com.myapplication.presentation.main.bookmark.components.DeleteDialog
import com.myapplication.presentation.main.common.TitleSection
import com.myapplication.presentation.navgraph.Route
import com.myapplication.util.ResourceResponse
import com.myapplication.util.extractDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun BookmarkScreen(
	viewModel: BookmarkViewModel,
	navController: NavController,
	sharedViewModel: SharedViewModel,
	snackbarHostState:  SnackbarHostState
) {
	val context = LocalContext.current
	var showDeleteDialog = remember {
		mutableStateOf(false)
	}
	var deleteArticle: CommonArticle? = null
	val coroutineScope: CoroutineScope = rememberCoroutineScope()
	val result = viewModel.savedArticles.collectAsStateWithLifecycle().value

	Surface (
		modifier = Modifier
			.fillMaxSize()
	) {
		Column(
			modifier = Modifier
				.padding(all = 10.dp)
		) {
			Spacer(modifier = Modifier.height(Dimens.LargePadding3))

			TitleSection(
				screenTitle = "Bookmark",
				screenDescription = "Your saved news",
				style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
			)

			Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

			when(result) {
				is ResourceResponse.Loading -> {
					Column(
						modifier = Modifier.fillMaxSize(),
						verticalArrangement = Arrangement.Center,
						horizontalAlignment = Alignment.CenterHorizontally
					) {
						CircularProgressIndicator()
					}
				}
				is ResourceResponse.Success -> {
					LazyColumn {
						result.data?.let { data ->
							items(data.size) { count ->
								ListCardItem(
									article = data[count],
									context = context,
									onClick = { article ->
										sharedViewModel.setArticle(article)
										navController.navigate(Route.DetailScreen.route)
									},
									onLongPress = { article ->
										deleteArticle = article
										showDeleteDialog.value = !showDeleteDialog.value
									}
								)
							}
						}
					}
				}
				is ResourceResponse.Error -> {
					LaunchedEffect(true)  {
						coroutineScope.launch {
							snackbarHostState.showSnackbar(message = result.message.toString())
						}
					}
				}
				is ResourceResponse.ideal -> TODO()
			}

			if (showDeleteDialog.value) {
				DeleteDialog(
					onDelete = {
						showDeleteDialog.value = !showDeleteDialog.value
						viewModel.deleteArticle(deleteArticle!!)
					},
					onDismiss = {
						showDeleteDialog.value = !showDeleteDialog.value
					}
				)
			}

			LaunchedEffect(true) {
				viewModel.articleDelete.collectLatest { result ->
					if (result != -1) {
						snackbarHostState.showSnackbar(message = "Article Deleted", actionLabel = "Undo", duration = SnackbarDuration.Long)
					} else {
						snackbarHostState.showSnackbar(message = "Ooops! something went wrong")
					}
				}
			}
		}//: Column
	}//: Surface
}

@Composable
private fun ListCardItem(
	article: CommonArticle,
	context: Context,
	onClick: (CommonArticle) -> Unit,
	onLongPress: (CommonArticle) -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(all = 5.dp)
			.border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
			.pointerInput(Unit) {
				detectTapGestures(
					onTap = {
						onClick(article)
					},
					onLongPress = {
						onLongPress(article)
					}
				)
			}
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(all = 5.dp)
		) {
			AsyncImage(
				model = ImageRequest.Builder(context).data(article.image ?: "").build(),
				contentDescription = null,
				modifier = Modifier
					.size(Dimens.ArticleImage)
					.clip(
						RoundedCornerShape(10.dp)
					),
				contentScale = ContentScale.Crop
			)

			Column(
				modifier = Modifier.padding(Dimens.SmallPadding1)
			) {
				Text(
					text = article.title ?: "",
					maxLines = 3,
					minLines = 3,
					overflow = TextOverflow.Ellipsis,
					style = MaterialTheme.typography.titleMedium
				)

				Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

				Row(
					modifier = Modifier.fillMaxWidth(),
					verticalAlignment = Alignment.CenterVertically
				) {
					Text(
						article.getArticleSource()?.name ?: "",
						style = MaterialTheme.typography.labelMedium,
						modifier = Modifier.weight(3f)
					)

					article.publishedAt?.let {
						Row(
							modifier = Modifier.weight(3f),
							verticalAlignment = Alignment.CenterVertically
						) {
							Icon(
								painter = painterResource(id = R.drawable.clock),
								contentDescription = null,
								modifier = Modifier
									.size(13.dp)
							)

							Spacer(modifier = Modifier.width(3.dp))

							Text(
								extractDate(it),
								style = MaterialTheme.typography.labelMedium
							)
						}
					}
				}//: Row
			}//: Column
		}//: Row
	}//: Column
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
	BookmarkScreen(
		viewModel = hiltViewModel(),
		navController = rememberNavController(),
		sharedViewModel = hiltViewModel(),
		snackbarHostState = SnackbarHostState()
	)
}