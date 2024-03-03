package com.cremasfood.app.features.search.navigation

import com.cremasfood.app.domain.model.recipe.RecipeDomain

interface SearchNavigation {
    fun navigateToDetails(recipe: RecipeDomain?)
    fun popBackStack()
}