package com.cremasfood.app.features.search.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.cremasfood.app.domain.model.recipe.RecipeDomain
import com.cremasfood.app.extensions.features.rememberFlowWithLifecycle
import com.cremasfood.app.features.search.navigation.SearchNavigation
import com.cremasfood.app.features.search.state.SearchState
import com.cremasfood.app.features.search.viewmodel.SearchViewModel
import com.cremasfood.app.ui.components.apierror.ApiErrorScreen
import com.cremasfood.app.ui.components.loading.Loading
import com.cremasfood.app.ui.components.recipecard.RecipeCard
import com.cremasfood.app.ui.components.search.SearchNotFound
import com.cremasfood.app.ui.components.toolbar.SearchToolbar
import com.cremasfood.app.utils.constants.Constants.Numbers.KEY_NUMBER_ZERO
import com.cremasfood.app.utils.constants.Constants.Text.EMPTY_STRING_DEFAULT
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.inject

@Composable
fun SearchScreen(
    searchText: String?,
    viewModel: SearchViewModel = getViewModel()
) {

    val state by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = SearchState.EMPTY)

    LaunchedEffect(Unit) {
        viewModel.dispatchViewAction(searchText = searchText ?: EMPTY_STRING_DEFAULT)
    }

    Search(
        searchText = searchText ?: EMPTY_STRING_DEFAULT,
        state = state,
        viewModel = viewModel
    )
}

@Composable
fun Search(
    searchText: String,
    state: SearchState,
    viewModel: SearchViewModel
) {
    val pagedList = state.recipes.collectAsLazyPagingItems()
    val text = remember { mutableStateOf(searchText) }
    val navigation: SearchNavigation by inject()

    SearchToolbar(
        searchText = text.value,
        goBack = false,
        onSearchClick = {
            if (text.value.isNotEmpty()) {
                viewModel.dispatchViewAction(searchText = text.value)
            }
        },
        onNavigateUpClick = {
            navigation.popBackStack()
        },
        contentBottom = {},
        onTextChanged = {
            text.value = it
        },
        content = {
            when {
                pagedList.loadState.refresh is LoadState.Loading -> {
                    Loading()
                }

                pagedList.loadState.refresh is LoadState.Error ||
                        pagedList.loadState.append is LoadState.Error ||
                        pagedList.loadState.prepend is LoadState.Error -> {
                    SearchNotFound(search = text.value)
                }

                pagedList.loadState.append.endOfPaginationReached -> {
                    if (pagedList.itemCount == KEY_NUMBER_ZERO)
                        SearchNotFound(search = text.value)
                    else
                        ShowPagedListSearch(
                            pagedList = pagedList,
                            navigation = navigation
                        )
                }

                else -> {
                    ShowPagedListSearch(
                        pagedList = pagedList,
                        navigation = navigation
                    )
                }
            }
        }
    )
}

@Composable
fun ShowPagedListSearch(
    pagedList: LazyPagingItems<RecipeDomain>,
    navigation: SearchNavigation
) {
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