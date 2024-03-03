package com.cremasfood.app.features.search.view

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.cremasfood.app.extensions.features.rememberFlowWithLifecycle
import com.cremasfood.app.features.search.navigation.SearchNavigation
import com.cremasfood.app.features.search.state.SearchState
import com.cremasfood.app.features.search.viewmodel.SearchViewModel
import com.cremasfood.app.ui.components.loading.Loading
import com.cremasfood.app.ui.components.recipecard.RecipeCard
import com.cremasfood.app.ui.components.toolbar.SearchToolbar
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.inject

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchText: String,
    viewModel: SearchViewModel = getViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.dispatchViewAction(searchText = searchText)
    }

    val state by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = SearchState.EMPTY)

    Search(modifier = modifier, searchText = searchText, state = state)
}

@Composable
fun Search(
    modifier: Modifier,
    searchText: String,
    state: SearchState
) {
    val pagedList = state.recipes.collectAsLazyPagingItems()
    val text = remember { mutableStateOf(searchText) }
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val navigation: SearchNavigation by inject()

    SearchToolbar(
        searchText = text.value,
        goBack = false,
        onSearchClick = {},
        onNavigateUpClick = {
            dispatcher?.onBackPressed()
        },
        contentBottom = {},
        onTextChanged = {
            text.value = it
        },
        content = {
            when (pagedList.loadState.refresh) {
                is LoadState.Loading -> {
                    Loading()
                }

                is LoadState.Error -> {}

                else -> {
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 16.dp)
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