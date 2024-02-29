package com.cremasfood.app.features.home.view

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.cremasfood.app.features.home.viewmodel.HomeViewModel
import com.cremasfood.app.ui.components.toolbar.SearchToolbar
import com.cremasfood.app.utils.Constants.Text.EMPTY_STRING_DEFAULT
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = getViewModel()
) {

    Home(modifier = modifier)
}

@Composable
fun Home(
    modifier: Modifier
) {
    val text = remember { mutableStateOf(EMPTY_STRING_DEFAULT) }
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    SearchToolbar(
        searchText = text.value,
        goBack = false,
        onSearchClick = {},
        onNavigateUpClick = {
            dispatcher?.onBackPressed()
        },
        contentBottom = {

        },
        onTextChanged = {
            text.value = it
        },
        content = {}
    )
}