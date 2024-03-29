package com.cremasfood.app.features.home.view

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.cremasfood.app.extensions.features.rememberFlowWithLifecycle
import com.cremasfood.app.features.home.navigation.HomeNavigation
import com.cremasfood.app.features.home.state.HomeState
import com.cremasfood.app.features.home.viewmodel.HomeViewModel
import com.cremasfood.app.ui.components.apierror.ApiErrorScreen
import com.cremasfood.app.ui.components.loading.Loading
import com.cremasfood.app.ui.components.recipecard.RecipeCard
import com.cremasfood.app.ui.components.toolbar.SearchToolbar
import com.cremasfood.app.utils.constants.Constants
import com.cremasfood.app.utils.constants.Constants.Numbers.KEY_NUMBER_ZERO
import com.cremasfood.app.utils.constants.Constants.Text.EMPTY_STRING_DEFAULT
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.inject

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel()
) {
    val state by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = HomeState.EMPTY)

    Home(state = state, viewModel = viewModel)
}

@Composable
fun Home(
    state: HomeState,
    viewModel: HomeViewModel
) {
    val pagedList = state.recipes.collectAsLazyPagingItems()
    val text = remember { mutableStateOf(EMPTY_STRING_DEFAULT) }
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val navigation: HomeNavigation by inject()

    SearchToolbar(
        searchText = text.value,
        goBack = false,
        onSearchClick = {
            if (text.value.isNotEmpty()) {
                navigation.navigateToSearch(searchText = text.value)
            }
        },
        onNavigateUpClick = {
            dispatcher?.onBackPressed()
        },
        contentBottom = {

        },
        onTextChanged = {
            text.value = it
        },
        content = {
            when {
                pagedList.loadState.refresh is LoadState.Loading ||
                        pagedList.loadState.append is LoadState.Loading -> {
                    Loading()
                }

                pagedList.loadState.refresh is LoadState.Error ||
                        pagedList.loadState.append is LoadState.Error ||
                        pagedList.loadState.prepend is LoadState.Error -> {
                    ApiErrorScreen {
                        viewModel.getAllRecipesUseCase()
                    }
                }

                else -> {
                    if (pagedList.itemCount == KEY_NUMBER_ZERO) {
                        ApiErrorScreen {
                            viewModel.getAllRecipesUseCase()
                        }
                    } else {
                        LazyColumn(
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(pagedList.itemCount) { index ->
                                pagedList[index]?.let { cardItem ->
                                    RecipeCard(
                                        recipe = cardItem,
                                        onClick = {
                                            navigation.navigateToDetails(recipe = cardItem)
                                        })
                                }
                            }

                            if (pagedList.loadState.append == LoadState.Loading) {
                                item {
                                    Loading()
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}