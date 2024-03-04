package com.cremasfood.app.features.home.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cremasfood.app.domain.usecase.recipes.GetAllRecipesUseCase
import com.cremasfood.app.features.home.state.HomeState
import com.cremasfood.app.utils.internet.CheckInternet.isInternetAvailable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAllRecipesUseCase: GetAllRecipesUseCase
): ViewModel() {

    private var _state: MutableStateFlow<HomeState> =
        MutableStateFlow(HomeState.EMPTY)
    var state: StateFlow<HomeState> = _state

    fun checkInternetConnection(context: Context) {
        if (!isInternetAvailable(context)) {
            _state.value = _state.value.copy(checkInternet = false)
        } else {
            _state.value = _state.value.copy(checkInternet = true)
            getAllRecipesUseCase()
        }
    }

    private fun getAllRecipesUseCase() {
        viewModelScope.launch {
            val list = getAllRecipesUseCase.getAllRecipes()
            _state.value = _state.value.copy(recipes = list)
        }
    }
}