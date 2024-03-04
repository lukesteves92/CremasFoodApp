package com.cremasfood.app.features.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cremasfood.app.data.utils.singleorthrow.singleOrThrow
import com.cremasfood.app.domain.usecase.recipes.GetAllRecipesUseCase
import com.cremasfood.app.features.search.state.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getAllRecipesUseCase: GetAllRecipesUseCase
): ViewModel() {
    private var _state: MutableStateFlow<SearchState> =
        MutableStateFlow(SearchState.EMPTY)
    var state: StateFlow<SearchState> = _state

    fun dispatchViewAction(searchText: String) {
        getAllRecipesUseCase(searchText = searchText)
    }
    private fun getAllRecipesUseCase(searchText: String) {
        viewModelScope.launch {
            val pagedList = getAllRecipesUseCase.getAllRecipes(search = searchText)
            _state.value = _state.value.copy(recipes = pagedList)
        }
    }
}