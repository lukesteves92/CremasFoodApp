package com.cremasfood.app.domain.usecase.recipes

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cremasfood.app.data.paging.RecipesPagingSource
import com.cremasfood.app.domain.model.recipe.RecipeDomain
import com.cremasfood.app.domain.repository.CremasFoodRepository
import com.cremasfood.app.utils.Constants.Numbers.KEY_NUMBER_TEN
import kotlinx.coroutines.flow.Flow

class GetAllRecipesUseCase(
    private val repository: CremasFoodRepository
) {
    fun getAllRecipes(
        search: String? = null,
        recipeGroupId: String? = null
    ): Flow<PagingData<RecipeDomain>> {
        return Pager(pageConfig) {
            RecipesPagingSource(
                repository = repository,
                recipeGroupId = recipeGroupId,
                search = search,
                take = pageConfig.pageSize
            )
        }.flow
    }

    companion object {
        private val pageConfig = PagingConfig(
            pageSize = KEY_NUMBER_TEN
        )
    }
}