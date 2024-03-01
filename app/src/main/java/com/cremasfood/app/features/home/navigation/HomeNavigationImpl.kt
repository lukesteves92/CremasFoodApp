package com.cremasfood.app.features.home.navigation

import com.cremasfood.app.domain.model.recipe.RecipeDomain
import com.cremasfood.app.navigation.manager.NavigationManager
import com.cremasfood.app.navigation.screen.Screen

class HomeNavigationImpl(
    private val navManager: NavigationManager
): HomeNavigation {
    override fun navigateToDetails(recipe: RecipeDomain?) {
        navManager.navigate(Screen.Details.createRoute(recipe = recipe))
    }

    override fun popBackStack() {
        navManager.popBackStack()
    }
}