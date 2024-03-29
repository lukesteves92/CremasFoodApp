package com.cremasfood.app.features.home.navigation

import com.cremasfood.app.domain.model.recipe.RecipeDomain

interface HomeNavigation {
    fun navigateToSearch(searchText: String)
    fun navigateToDetails(recipe: RecipeDomain?)
    fun popBackStack()
}