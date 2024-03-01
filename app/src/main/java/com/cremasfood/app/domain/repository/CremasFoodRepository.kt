package com.cremasfood.app.domain.repository

import com.cremasfood.app.data.remote.services.responseapi.ResponseApi
import com.cremasfood.app.domain.model.country.CountryDomain
import com.cremasfood.app.domain.model.recipe.RecipeDomain
import com.cremasfood.app.domain.model.recipegroup.RecipeGroupDomain
import kotlinx.coroutines.flow.Flow

interface CremasFoodRepository {
    fun getRecipeById(id: String): Flow<RecipeDomain>
    fun getAllRecipeGroups(): Flow<List<RecipeGroupDomain>>
    fun getAllCountry(): Flow<List<CountryDomain>>
    suspend fun getAllRecipes(
        search: String? = null,
        recipeGroupId: String? = null,
        skip: Int,
        take: Int
    ): ResponseApi
}