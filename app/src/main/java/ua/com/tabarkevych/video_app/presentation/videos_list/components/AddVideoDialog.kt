package ua.com.tabarkevych.video_app.presentation.videos_list.components

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import ua.com.tabarkevych.video_app.R
import ua.com.tabarkevych.video_app.presentation.ui.theme.CustomThemeManager

@Composable
fun CustomDialog(
    openDialogCustom: MutableState<Boolean>,
    onPhotoAdded: (Uri?, String, String) -> Unit
) {
    Dialog(onDismissRequest = { openDialogCustom.value = false }) {
        CustomDialogUI(openDialogCustom = openDialogCustom) { image, title, description ->
            onPhotoAdded(image, title, description)
        }
    }
}


//Layout
@Composable
fun CustomDialogUI(
    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>,
    onPhotoAdded: (Uri?, String, String) -> Unit
) {
    var textTitle by rememberSaveable { mutableStateOf("") }
    var textDescription by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    var imageUri by remember { mutableStateOf<Uri?>(Uri.parse("android.resource://" + context.packageName + "/" + R.drawable.ic_plus)) }
    var isImageAdded by rememberSaveable { mutableStateOf(false) }

    val pickMedia = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            isImageAdded = true
            imageUri = uri
        }
    }
    Card(
        modifier = Modifier
            .padding(10.dp, 5.dp, 10.dp, 10.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = CustomThemeManager.colors.backgroundPrimary,
        contentColor = CustomThemeManager.colors.backgroundSecondary,
        elevation = 8.dp
    ) {
        Column {
            Text(
                text = "Add Image",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp,
                color = CustomThemeManager.colors.textPrimary,
            )

            AsyncImage(
                model = imageUri,
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth()
                    .clickable { pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) },
                contentDescription = "Select Image", // decorative
            )

            OutlinedTextField(
                modifier = Modifier.padding(10.dp),
                value = textTitle,
                onValueChange = { textTitle = it },
                label = { Text("Title*", color = CustomThemeManager.colors.textPrimary) },
                textStyle = LocalTextStyle.current.copy(color = CustomThemeManager.colors.textPrimary),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = CustomThemeManager.colors.textPrimary,
                    unfocusedBorderColor = CustomThemeManager.colors.textPrimary)
            )

            OutlinedTextField(
                modifier = Modifier.padding(10.dp),
                value = textDescription,
                onValueChange = { textDescription = it },
                label = { Text("Description*",color = CustomThemeManager.colors.textPrimary) },
                textStyle = LocalTextStyle.current.copy(color = CustomThemeManager.colors.textPrimary),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = CustomThemeManager.colors.textPrimary,
                    unfocusedBorderColor = CustomThemeManager.colors.textPrimary)
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(CustomThemeManager.colors.buttonBackgroundColor),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                TextButton(onClick = {
                    openDialogCustom.value = false
                }) {
                    Text(
                        "Complete",
                        fontWeight = FontWeight.ExtraBold,
                        color = CustomThemeManager.colors.textPrimary,
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 5.dp)
                            .clickable {
                                if (isImageAdded) {
                                    onPhotoAdded(imageUri, textTitle, textDescription)
                                    openDialogCustom.value = false
                                }
                            }
                    )
                }
            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview(name = "Custom Dialog")
@Composable
fun MyDialogUIPreview() {
    CustomDialogUI(openDialogCustom = mutableStateOf(false)) { _, _, _ -> }
}