package com.cremasfood.app.domain.usecase.recipebyid

import com.cremasfood.app.domain.model.recipe.RecipeDomain
import com.cremasfood.app.domain.repository.CremasFoodRepository
import kotlinx.coroutines.flow.Flow

class GetRecipeByIdUseCase(
    private val repository: CremasFoodRepository
) {
    fun getRecipeById(id: String): Flow<RecipeDomain> = repository.getRecipeById(id = id)
}