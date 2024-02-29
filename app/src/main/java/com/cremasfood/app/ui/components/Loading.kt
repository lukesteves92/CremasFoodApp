package com.cremasfood.app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cremasfood.app.ui.color.OrangeMain

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Loading() {
    CircularProgressIndicator(
        color = OrangeMain,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}