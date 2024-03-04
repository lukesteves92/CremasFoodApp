package com.cremasfood.app.features.home.state

import androidx.paging.PagingData
import com.cremasfood.app.domain.model.recipe.RecipeDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class HomeState(
    val showError: Boolean = false,
    val recipes: Flow<PagingData<RecipeDomain>> = flowOf(PagingData.empty())
) {
    companion object {
        val EMPTY = HomeState()
    }
}