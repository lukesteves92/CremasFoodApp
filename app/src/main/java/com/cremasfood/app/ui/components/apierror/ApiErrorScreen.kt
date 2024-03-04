package com.cremasfood.app.ui.components.apierror

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.cremasfood.app.R
import com.cremasfood.app.ui.color.BackgroundColor
import com.cremasfood.app.ui.color.LightBlack
import com.cremasfood.app.ui.color.OrangeMain
import com.cremasfood.app.ui.components.button.ButtonOutline

@Composable
fun ApiErrorScreen(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.wrapContentSize(),
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(R.drawable.cremas_ic_error).apply(block = fun ImageRequest.Builder.() {
                    error(R.drawable.cremas_ic_error)
                }).build()
            ),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.alpha(0.75f),
            text = stringResource(id = R.string.cremas_tv_title_apierror),
            style = MaterialTheme.typography.h5,
            color = OrangeMain,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.alpha(0.5f),
            text = stringResource(id = R.string.cremas_tv_body_apierror),
            style = MaterialTheme.typography.subtitle2,
            color = LightBlack,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(48.dp))

        ButtonOutline(
            border = BorderStroke(1.dp, OrangeMain),
            textStyle = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            ),
            text = stringResource(id = R.string.cremas_re_try),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
            onClick = {
                onClick()
            }
        )
    }
}