package com.cremasfood.app.domain.usecase.recipegroups

import com.cremasfood.app.domain.model.recipegroup.RecipeGroupDomain
import com.cremasfood.app.domain.repository.CremasFoodRepository
import kotlinx.coroutines.flow.Flow

class GetAllRecipeGroupsUseCase(
    private val repository: CremasFoodRepository
) {
    fun getAllRecipeGroups(): Flow<List<RecipeGroupDomain>> = repository.getAllRecipeGroups()
}