package com.cremasfood.app.domain.model.recipeingredient

import android.os.Parcelable
import com.cremasfood.app.domain.model.ingredient.IngredientDomain
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeIngredientDomain(
    val id: String? = null,
    val recipeId: String? = null,
    val quantity: Double? = null,
    val unit: String? = null,
    val ingredient: IngredientDomain? = null
): Parcelable
