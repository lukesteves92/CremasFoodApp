package com.cremasfood.app.features.details.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.cremasfood.app.R
import com.cremasfood.app.domain.model.recipe.RecipeDomain
import com.cremasfood.app.domain.model.recipeingredient.RecipeIngredientDomain
import com.cremasfood.app.features.details.navigation.DetailsNavigation
import com.cremasfood.app.ui.color.BackgroundColor
import com.cremasfood.app.ui.color.LightBlack
import com.cremasfood.app.ui.color.OrangeMain
import com.cremasfood.app.ui.components.toolbar.ToolbarSearch
import com.cremasfood.app.utils.Constants.Text.EMPTY_STRING_DEFAULT
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

    val navigation: DetailsNavigation by inject()

    Scaffold(
        topBar = {
            ToolbarSearch(title = recipe?.title, onBackPressed = {
                navigation.popBackStack()
            })
        },
        backgroundColor = BackgroundColor,
        content = {
            it
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .scrollable(rememberScrollState(), Orientation.Horizontal)
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(recipe?.imageBase64),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.FillBounds,
                )
                Text(
                    text = recipe?.title ?: EMPTY_STRING_DEFAULT,
                    modifier = Modifier.padding(top = 16.dp, bottom = 6.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = LightBlack,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )

                Chip(
                    modifier = Modifier.padding(end = 6.dp),
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

@Composable
fun Ingredients(ingredients: List<RecipeIngredientDomain>) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.recipe_details_ingredients_title),
                overflow = TextOverflow.Ellipsis,
                color = LightBlack,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
            )
            Text(
                text = "${ingredients.size} " + stringResource(R.string.recipe_details_ingredient_items),
                overflow = TextOverflow.Ellipsis,
                color = LightBlack,
                fontSize = 15.sp,
                textAlign = TextAlign.Start,
            )
        }
        LazyRow {
            items(ingredients.size) { index ->
                IngredientItem(ingredients[index])
            }
        }
    }
}

@Composable
fun IngredientItem(ingredient: RecipeIngredientDomain) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 4.dp),
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFf8f8f8))
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .width(110.dp)
                    .padding(top = 6.dp, bottom = 4.dp),
                text = ingredient.ingredient?.name ?: EMPTY_STRING_DEFAULT,
                overflow = TextOverflow.Ellipsis,
                color = OrangeMain,
                maxLines = 1,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
            )
        }

    }
}