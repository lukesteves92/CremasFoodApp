package com.cremasfood.app.features.home.view

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.cremasfood.app.extensions.features.rememberFlowWithLifecycle
import com.cremasfood.app.features.home.navigation.HomeNavigation
import com.cremasfood.app.features.home.state.HomeState
import com.cremasfood.app.features.home.viewmodel.HomeViewModel
import com.cremasfood.app.ui.components.loading.Loading
import com.cremasfood.app.ui.components.recipecard.RecipeCard
import com.cremasfood.app.ui.components.toolbar.SearchToolbar
import com.cremasfood.app.utils.Constants.Text.EMPTY_STRING_DEFAULT
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.inject

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = getViewModel()
) {

    val state by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = HomeState.EMPTY)

    Home(modifier = modifier, state = state)
}

@Composable
fun Home(
    modifier: Modifier,
    state: HomeState
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
            when (pagedList.loadState.refresh) {
                is LoadState.Loading -> {
                    Loading()
                }

                is LoadState.Error -> {}

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