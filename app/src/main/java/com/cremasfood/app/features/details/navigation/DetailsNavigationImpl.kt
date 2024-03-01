package com.cremasfood.app.features.details.navigation

import com.cremasfood.app.navigation.manager.NavigationManager

class DetailsNavigationImpl(
    private val navManager: NavigationManager
): DetailsNavigation {
    override fun popBackStack() {
        navManager.popBackStack()
    }
}