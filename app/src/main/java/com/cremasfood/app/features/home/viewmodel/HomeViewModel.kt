package com.cremasfood.app.features.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cremasfood.app.data.utils.singleorthrow.singleOrThrow
import com.cremasfood.app.domain.usecase.recipes.GetAllRecipesUseCase
import com.cremasfood.app.features.home.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAllRecipesUseCase: GetAllRecipesUseCase
): ViewModel() {

    private var _state: MutableStateFlow<HomeState> =
        MutableStateFlow(HomeState.EMPTY)
    var state: StateFlow<HomeState> = _state

    init {
        getAllRecipesUseCase()
    }

    fun getAllRecipesUseCase() {
        viewModelScope.launch {
            getAllRecipesUseCase.getAllRecipes().singleOrThrow(
                success = { pagedList ->
                    _state.value = _state.value.copy(recipes = flowOf(pagedList), showError = false)
                },
                error = {
                    _state.value = _state.value.copy(showError = true)
                }
            )
        }
    }
}