package com.cremasfood.app.features.details.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.cremasfood.app.R
import com.cremasfood.app.domain.model.recipe.RecipeDomain
import com.cremasfood.app.domain.model.recipeingredient.RecipeIngredientDomain
import com.cremasfood.app.extensions.string.convertLat
import com.cremasfood.app.extensions.string.convertLong
import com.cremasfood.app.features.details.navigation.DetailsNavigation
import com.cremasfood.app.ui.color.BackgroundColor
import com.cremasfood.app.ui.color.LightBlack
import com.cremasfood.app.ui.color.OrangeMain
import com.cremasfood.app.ui.components.bottomsheet.main.SheetLayout
import com.cremasfood.app.ui.components.button.ButtonOutline
import com.cremasfood.app.ui.components.toolbar.ToolbarSearch
import com.cremasfood.app.utils.Constants.Numbers.KEY_DEFAULT_LAT_LONG
import com.cremasfood.app.utils.Constants.Numbers.KEY_DURATION_ANIMATION
import com.cremasfood.app.utils.Constants.Text.EMPTY_STRING_DEFAULT
import kotlinx.coroutines.launch
import org.koin.androidx.compose.inject

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    recipe: RecipeDomain?
) {
    Details(modifier = modifier, recipe = recipe)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Details(
    modifier: Modifier = Modifier,
    recipe: RecipeDomain?
) {

    val animationSpec = remember {
        Animatable(0f)
            .run {
                TweenSpec<Float>(durationMillis = KEY_DURATION_ANIMATION)
            }
    }
    val modalState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { false },
        skipHalfExpanded = true,
        animationSpec = animationSpec
    )
    val coroutineScope = rememberCoroutineScope()
    val navigation: DetailsNavigation by inject()

    ModalBottomSheetLayout(
        sheetState = modalState,
        sheetContent = {
            SheetLayout(
                lat = recipe?.country?.geoCoordinates?.convertLat()?.toDouble() ?: KEY_DEFAULT_LAT_LONG,
                long = recipe?.country?.geoCoordinates?.convertLong()?.toDouble() ?: KEY_DEFAULT_LAT_LONG,
                onCloseBottomSheet = {
                    coroutineScope.launch {
                        modalState.hide()
                    }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                ToolbarSearch(title = recipe?.title, onBackPressed = {
                navigation.popBackStack()
                })
            },
            bottomBar = {
                ButtonOutline(
                    border = BorderStroke(1.dp, OrangeMain),
                    textStyle = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    ),
                    text = stringResource(id = R.string.bt_cremas_maps),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
                    onClick = {
                        coroutineScope.launch {
                            modalState.show()
                        }
                    }
                )
            },
            backgroundColor = BackgroundColor,
            content = {
                it
                Column(
                    modifier = modifier
                        .padding(20.dp)
                        .scrollable(rememberScrollState(), Orientation.Horizontal)
                        .fillMaxSize()
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(recipe?.imageBase64)
                                .apply(block = fun ImageRequest.Builder.() {
                                    error(R.drawable.no_image_available)
                                    placeholder(R.drawable.no_image_available)
                                }).build()
                        ),
                        contentDescription = null,
                        modifier = modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.FillBounds,
                    )
                    Text(
                        text = recipe?.title ?: EMPTY_STRING_DEFAULT,
                        modifier = modifier.padding(top = 16.dp, bottom = 6.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = LightBlack,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                    )

                    Text(
                        text = recipe?.description ?: EMPTY_STRING_DEFAULT,
                        maxLines = 3,
                        color = LightBlack,
                        fontSize = 15.sp,
                    )

                    Chip(
                        modifier = modifier.padding(end = 6.dp),
                        onClick = { },
                        border = BorderStroke(
                            ChipDefaults.OutlinedBorderSize, OrangeMain
                        ),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = BackgroundColor, contentColor = OrangeMain
                        ),
                    ) {
                        Text(
                            recipe?.country?.name ?: EMPTY_STRING_DEFAULT
                        )
                    }

                    Ingredients(ingredients = recipe?.recipeIngredient ?: listOf())
                }
            }
        )
    }
}

@Composable
fun Ingredients(ingredients: List<RecipeIngredientDomain>) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.recipe_details_ingredients_title),
                overflow = TextOverflow.Ellipsis,
                color = LightBlack,
                fontSize = 18.sp,
                textAlign = TextAlign.Start,
            )
            Text(
                text = "${ingredients.size} " + stringResource(R.string.recipe_details_ingredient_items),
                overflow = TextOverflow.Ellipsis,
                color = LightBlack,
                fontSize = 15.sp,
                textAlign = TextAlign.End,
            )
        }
        LazyRow(
            modifier = Modifier
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(ingredients.size) { index ->
                IngredientItem(ingredients[index])
            }
        }
    }
}

@Composable
fun IngredientItem(ingredient: RecipeIngredientDomain) {
    Surface(
        shape = RoundedCornerShape(100.dp),
        color = Color.White.copy(alpha = 0.6f)
    ) {
        Text(
            modifier = Modifier.padding(start = 14.dp, end = 14.dp, top = 8.dp, bottom = 10.dp),
            text = ingredient.ingredient?.name?.lowercase() ?: EMPTY_STRING_DEFAULT,
            color = OrangeMain,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
        )
    }
}