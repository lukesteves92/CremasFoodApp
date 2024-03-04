package com.cremasfood.app.features.home.view

import android.content.Context
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
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
import com.cremasfood.app.utils.constants.Constants.Text.EMPTY_STRING_DEFAULT
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.inject

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel()
) {
    val context = LocalContext.current
    val state by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = HomeState.EMPTY)

    LaunchedEffect(Unit) {
        viewModel.checkInternetConnection(context = context)
    }

    Home(state = state, viewModel = viewModel, context = context)
}

@Composable
fun Home(
    state: HomeState,
    viewModel: HomeViewModel,
    context: Context
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
                !state.checkInternet -> {
                    ApiErrorScreen {
                        viewModel.checkInternetConnection(context = context)
                    }
                }

                pagedList.loadState.refresh is LoadState.Loading -> {
                    Loading()
                }

                pagedList.loadState.refresh is LoadState.Error -> {
                    ApiErrorScreen {
                        viewModel.checkInternetConnection(context = context)
                    }
                }

                else -> {
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
    )
}