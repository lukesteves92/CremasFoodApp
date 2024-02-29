package com.cremasfood.app.main.view

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cremasfood.app.navigation.nav.Navigation
import com.cremasfood.app.navigation.screen.Screen
import com.cremasfood.app.ui.color.BackgroundColor
import com.cremasfood.app.ui.theme.CremasFoodTheme
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setContent {
            val navController = rememberNavController()
            CremasFoodTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundColor
                ) {
                    NavigateSplash(navController = navController)
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavigateSplash(navController: NavHostController) {
    Navigation(navController = navController, Screen.Splash.route)
}