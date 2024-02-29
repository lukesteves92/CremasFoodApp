package com.cremasfood.app.ui.components.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cremasfood.app.R
import com.cremasfood.app.ui.color.BackgroundColor
import com.cremasfood.app.ui.color.OrangeMain
import com.cremasfood.app.ui.components.textfields.CustomTextField
import com.cremasfood.app.utils.Constants.Text.EMPTY_STRING_DEFAULT

@Composable
fun ToolbarSearch(
    modifier: Modifier = Modifier,
    onBackPressed: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = R.string.cremas_tb_title),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = onBackPressed?.let { action ->
            {
                IconButton(onClick = action) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.cremas_content_accessibility_back)
                    )
                }
            }
        },
        backgroundColor = OrangeMain,
        contentColor = Color.White,
        elevation = 0.dp
    )
}

@Composable
fun SearchToolbar(
    modifier: Modifier = Modifier,
    goBack: Boolean = true,
    onSearchClick: () -> Unit = {},
    searchText: String = EMPTY_STRING_DEFAULT,
    onNavigateUpClick: () -> Unit = {},
    onTextChanged: (String) -> Unit,
    content: @Composable () -> Unit,
    contentBottom: @Composable () -> Unit = {},
) {
    Scaffold(
        topBar = {
            ToolbarSearch(onBackPressed = {
                onNavigateUpClick.invoke()
            })
        },
        backgroundColor = BackgroundColor,
        bottomBar = contentBottom
    ) { padding ->
        val hasSearch = remember { mutableStateOf(false) }

        Column {
            CustomTextField(
                modifier = modifier
                    .padding(bottom = 5.dp)
                    .background(OrangeMain),
                text = searchText,
                hintText = stringResource(id = R.string.cremas_search_recipe),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.cremas_ic_search),
                        contentDescription = null,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                },
                trailingIcon = { searchText, handler ->
                    searchText as String
                    if (searchText.isNotBlank()) {
                        Icon(
                            painter = painterResource(id = R.drawable.cremas_ic_clear_field),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                handler?.invoke()
                            }
                        )
                    }
                },
                onTextChange = {
                    hasSearch.value = it.isNotBlank()
                    onTextChanged(it)
                },
                onSearchClick = {
                    onSearchClick()
                },
                onClick = {
                    if (goBack) onNavigateUpClick.invoke()
                }
            )

            content()
        }
    }
}
