package com.cremasfood.app.features.search.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cremasfood.app.domain.usecase.recipes.GetAllRecipesUseCase
import com.cremasfood.app.features.search.state.SearchState
import com.cremasfood.app.utils.internet.CheckInternet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getAllRecipesUseCase: GetAllRecipesUseCase
): ViewModel() {
    private var _state: MutableStateFlow<SearchState> =
        MutableStateFlow(SearchState.EMPTY)
    var state: StateFlow<SearchState> = _state

    fun dispatchViewAction(searchText: String, context: Context) {
        if (!CheckInternet.isInternetAvailable(context)) {
            _state.value = _state.value.copy(checkInternet = false)
        } else {
            _state.value = _state.value.copy(checkInternet = true)
            getAllRecipesUseCase(searchText = searchText)
        }
    }
    private fun getAllRecipesUseCase(searchText: String) {
        viewModelScope.launch {
            val list = getAllRecipesUseCase.getAllRecipes(search = searchText)
            _state.value = _state.value.copy(recipes = list)
        }
    }
}