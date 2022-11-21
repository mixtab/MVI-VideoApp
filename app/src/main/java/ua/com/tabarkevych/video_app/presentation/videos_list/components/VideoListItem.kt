package ua.com.tabarkevych.video_app.presentation.videos_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ua.com.tabarkevych.video_app.domain.model.Video
import ua.com.tabarkevych.video_app.presentation.ui.theme.CustomThemeManager

@Composable
fun VideoListItem(
    video: Video,
    onItemClick: (Video) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CustomThemeManager.colors.backgroundPrimary)
            .clickable { onItemClick(video) }
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {


        AsyncImage(
            model = video.image,
            modifier = Modifier
                .height(70.dp)
                .wrapContentWidth(),
            contentDescription = "Select Image", // decorative
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp)
        ) {

            Text(
                text = video.title,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily.Serif,
                fontSize = 24.sp,
                color = CustomThemeManager.colors.textPrimary

            )
            Text(
                text = video.description,
                fontStyle = FontStyle.Italic,
                color = CustomThemeManager.colors.textSecondary
            )

        }
    }

}