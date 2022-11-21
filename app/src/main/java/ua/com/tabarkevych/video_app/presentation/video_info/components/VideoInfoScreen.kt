package ua.com.tabarkevych.video_app.presentation.video_info.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import ua.com.tabarkevych.video_app.presentation.navigation.Screen
import ua.com.tabarkevych.video_app.presentation.ui.theme.CustomThemeManager
import ua.com.tabarkevych.video_app.presentation.video_info.VideoInfoViewModel
import ua.com.tabarkevych.video_app.presentation.videos_list.VideosViewModel
import ua.com.tabarkevych.video_app.presentation.videos_list.components.VideoListItem

@Composable
fun VideoInfoScreen(
    viewModel: VideoInfoViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CustomThemeManager.colors.backgroundPrimary)
                .padding(20.dp)
        ) {

            AsyncImage(
                model = state.video?.image,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(20.dp),
                contentDescription = "Select Image", // decorative
            )

            Divider(color = Color.LightGray)

            Text(
                text = state.video?.title?:"",
                modifier = Modifier.padding(0.dp,20.dp),
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily.Serif,
                fontSize = 24.sp,
                color = CustomThemeManager.colors.textPrimary

            )
            Text(
                text = state.video?.description?:"",
                modifier = Modifier.padding(0.dp,10.dp),
                fontStyle = FontStyle.Italic,
                color = CustomThemeManager.colors.textSecondary
            )

        }


        if (state.error?.isNotBlank() == true) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.Center)

            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}