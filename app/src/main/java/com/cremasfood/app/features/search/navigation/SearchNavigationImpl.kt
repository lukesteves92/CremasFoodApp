package com.cremasfood.app.features.search.navigation

import com.cremasfood.app.domain.model.recipe.RecipeDomain
import com.cremasfood.app.navigation.manager.NavigationManager
import com.cremasfood.app.navigation.screen.Screen

class SearchNavigationImpl(
    private val navManager: NavigationManager
) : SearchNavigation {
    override fun navigateToDetails(recipe: RecipeDomain?) {
        navManager.navigate(Screen.Details.createRoute(recipe = recipe))
    }
    override fun popBackStack() {
        navManager.popBackStack()
    }
}