package com.cremasfood.app.features.search.state

import androidx.paging.PagingData
import com.cremasfood.app.domain.model.recipe.RecipeDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class SearchState(
    val recipes: Flow<PagingData<RecipeDomain>> = flowOf(PagingData.empty())
) {
    companion object {
        val EMPTY = SearchState()
    }
}
