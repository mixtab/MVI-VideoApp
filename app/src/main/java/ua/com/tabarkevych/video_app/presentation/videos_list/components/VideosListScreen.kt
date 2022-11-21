package ua.com.tabarkevych.video_app.presentation.videos_list.components

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ua.com.tabarkevych.video_app.R
import ua.com.tabarkevych.video_app.presentation.navigation.Screen
import ua.com.tabarkevych.video_app.presentation.ui.theme.CustomTheme
import ua.com.tabarkevych.video_app.presentation.ui.theme.CustomThemeManager
import ua.com.tabarkevych.video_app.presentation.videos_list.VideosViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VideosListScreen(
    navController: NavController,
    viewModel: VideosViewModel = hiltViewModel()
) {
    val openDialogCustom = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        if (viewModel.state.videosList.isNullOrEmpty()) {
            Text(
                text = "List is Empty. \n Please add new video",
                color = CustomThemeManager.colors.textPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                itemsIndexed(
                    items = viewModel.state.videosList ?: mutableListOf(),
                    key = { _, item -> item.hashCode() }) { _, video ->

                    val state = rememberDismissState(confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            viewModel.removeVideo(video.id)
                        }
                        true
                    })


                    SwipeToDismiss(state = state, background = {
                        val color = when (state.dismissDirection) {
                            DismissDirection.StartToEnd -> Color.Transparent
                            DismissDirection.EndToStart -> Color.Red
                            null -> Color.Transparent
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillParentMaxHeight()
                                .background(color)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(20.dp)
                            )
                        }
                    }, dismissContent = {
                        VideoListItem(video = video, onItemClick = {
                            navController.navigate(Screen.VideoInfoScreen.destination + "/${video.id}")
                        })
                    }, directions = setOf(DismissDirection.EndToStart))
                    Divider(color = Color.LightGray)
                }
            }
        }

        Column(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.BottomEnd)
                .background(CustomThemeManager.colors.backgroundPrimary)
                .padding(20.dp)
        ) {
            FloatingActionButton(
                modifier = Modifier.padding(10.dp),
                backgroundColor = CustomThemeManager.colors.buttonBackgroundColor,
                onClick = {
                    /*viewModel.setAppTheme(AppCompatDelegate.getDefaultNightMode())*/
                    CustomThemeManager.customTheme = if (CustomThemeManager.customTheme == CustomTheme.DARK) CustomTheme.LIGHT else CustomTheme.DARK
                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = if (CustomThemeManager.customTheme == CustomTheme.DARK) R.drawable.ic_sun else  R.drawable.ic_night_mode),
                    ""
                )
            }

            FloatingActionButton(
                modifier = Modifier.padding(10.dp),
                backgroundColor = CustomThemeManager.colors.buttonBackgroundColor,
                onClick = { openDialogCustom.value = true }) {

                Icon(Icons.Filled.Add, "")
            }
        }
        if (openDialogCustom.value)
            CustomDialog(openDialogCustom = openDialogCustom) { image, title, description ->
                viewModel.addVideo(image, title, description)
            }

        if (viewModel.state.error?.isNotBlank() == true) {
            Text(
                text = viewModel.state.error!!,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.Center)
            )
        }

        if (viewModel.state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}