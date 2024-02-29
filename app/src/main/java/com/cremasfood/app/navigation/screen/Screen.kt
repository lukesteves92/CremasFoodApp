package com.cremasfood.app.navigation.screen

sealed class Screen(val route: String) {
    data object Splash : Screen(route = "splash")
    data object Home : Screen(route = "home")
    data object Details : Screen(route = "Details")
}