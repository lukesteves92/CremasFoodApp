package com.cremasfood.app.ui.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cremasfood.app.R
import com.cremasfood.app.ui.color.LightBlack
import com.cremasfood.app.ui.color.OrangeMain
import com.cremasfood.app.ui.type.Typography
import com.cremasfood.app.utils.constants.Constants.Numbers.KEY_NUMBER_ONE
import com.cremasfood.app.utils.constants.Constants.Text.EMPTY_STRING_DEFAULT

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    trailingIcon: @Composable ((Any?, (() -> Unit)?) -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    hintText: String = EMPTY_STRING_DEFAULT,
    maxLines: Int = KEY_NUMBER_ONE,
    enabled: Boolean = true,
    transparentSearchBar: Boolean = false,
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClick: (String?) -> Unit,
    onClick: () -> Unit = {}
) {

    var showCancelBtn by remember { mutableStateOf(text.trim().isNotEmpty()) }
    val focusManager = LocalFocusManager.current
    var showLabel by remember { mutableStateOf(true) }
    var searchText by remember { mutableStateOf(text.trim()) }

    Row(
        modifier = Modifier
            .background(if (transparentSearchBar) Color.Transparent else OrangeMain),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicTextField(
            modifier = modifier
                .weight(4f, fill = true)
                .padding(horizontal = 16.dp)
                .defaultMinSize(minHeight = 40.dp)
                .onFocusChanged {
                    when {
                        it.isFocused -> showLabel = false
                    }
                }
                .clickable { onClick.invoke() }
                .focusable(),
            value = searchText,
            onValueChange = {
                showCancelBtn = true
                searchText = it
                onTextChange(it)
            },
            keyboardActions = KeyboardActions(onSearch = {
                onSearchClick(searchText)
                focusManager.clearFocus()
            }),
            singleLine = maxLines == KEY_NUMBER_ONE,
            maxLines = maxLines,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            textStyle = Typography.bodyMedium.copy(color = LightBlack),
            enabled = enabled,
            decorationBox = { innerTextField ->
                Row(
                    modifier
                        .background(color = Color.White, shape = RoundedCornerShape(100.dp))
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (leadingIcon != null) leadingIcon()
                        Spacer(modifier = Modifier.width(10.dp))
                        if ((searchText.isEmpty() || searchText == EMPTY_STRING_DEFAULT) && showLabel) {
                            Text(
                                hintText,
                                style = Typography.bodyMedium,
                                color = LightBlack
                            )
                        }
                        innerTextField()
                    }


                    if (trailingIcon != null) trailingIcon(searchText) {
                        searchText = EMPTY_STRING_DEFAULT
                    }
                }
            }
        )

        if (showCancelBtn) {
            Text(
                text = stringResource(id = R.string.bt_cremas_cancel),
                textAlign = TextAlign.Start,
                color = if (transparentSearchBar) LightBlack else Color.White,
                modifier = Modifier
                    .background(if (transparentSearchBar) Color.Transparent else OrangeMain)
                    .weight(1f, true)
                    .clickable {
                        showLabel = true
                        showCancelBtn = false
                        searchText = EMPTY_STRING_DEFAULT
                        focusManager.clearFocus()
                        onClick.invoke()
                    }
            )
        }
    }
}