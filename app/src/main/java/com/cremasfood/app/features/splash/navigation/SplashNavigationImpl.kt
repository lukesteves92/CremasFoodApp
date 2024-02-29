package com.cremasfood.app.features.splash.navigation

import com.cremasfood.app.navigation.manager.NavigationManager
import com.cremasfood.app.navigation.screen.Screen

internal class SplashNavigationImpl (
    private val navManager: NavigationManager
): SplashNavigation {
    override fun navigateToHome() {
        navManager.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
            launchSingleTop = true
        }
    }
}