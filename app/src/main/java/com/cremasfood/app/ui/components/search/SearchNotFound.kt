package com.cremasfood.app.ui.components.search

import android.view.Gravity
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.cremasfood.app.R
import com.cremasfood.app.ui.color.LightBlack
import com.cremasfood.app.ui.color.OrangeMain

@Composable
fun SearchNotFound(
    search: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 48.dp)
            .offset(y = (-20).dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.cremas_ic_megaphone),
            contentDescription = null,
            modifier = Modifier
                .size(180.dp)
        )
        Spacer(modifier = Modifier.height(54.dp))
        Text(
            text = stringResource(id = R.string.cremas_search_not_found_title),
            fontSize = 24.sp,
            color = OrangeMain,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        AndroidView(
            factory = { context -> TextView(context) },
            update = {
                it.text = HtmlCompat.fromHtml(
                    it.resources.getString(
                        R.string.cremas_search_not_found_body,
                        search
                    ),
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                )
                it.gravity = Gravity.CENTER
                it.textSize = 14F
                it.setTextColor(LightBlack.toArgb())
            }
        )

    }
}