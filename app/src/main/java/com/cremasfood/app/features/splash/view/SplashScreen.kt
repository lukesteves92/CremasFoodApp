package com.cremasfood.app.features.splash.view

import android.os.CountDownTimer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cremasfood.app.R
import com.cremasfood.app.features.splash.navigation.SplashNavigation
import com.cremasfood.app.ui.color.BackgroundColor
import com.cremasfood.app.ui.components.loading.Loading
import org.koin.androidx.compose.inject

@Composable
fun SplashScreen(
    modifier : Modifier = Modifier
){
    Splash(modifier = modifier)
}

@Composable
fun Splash(
    modifier : Modifier
){
    var timer: CountDownTimer? = null
    val navigation: SplashNavigation by inject()

    Scaffold(
        containerColor = BackgroundColor,
        bottomBar = {
            Box(
                modifier = modifier.fillMaxSize().padding(50.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Loading()
            }
        },
        content = { it
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val matrix = ColorMatrix()

                AsyncImage(
                    modifier = modifier
                        .size(300.dp),
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(R.drawable.cremas_main_logo)
                        .build(),
                    colorFilter = ColorFilter.colorMatrix(matrix),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                timer?.cancel()
                timer = object : CountDownTimer(3000, 3000) {
                    override fun onTick(p0: Long) {}
                    override fun onFinish() {
                        navigation.navigateToHome()
                    }
                }.start()
            }
        }
    )
}