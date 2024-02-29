package com.cremasfood.app.data.remote.model.recipeingredient

import com.cremasfood.app.data.remote.model.ingredient.IngredientResponse
import com.google.gson.annotations.SerializedName

data class RecipeIngredientResponse(
    @SerializedName(value = "Id")
    val id: String? = null,
    @SerializedName(value = "RecipeId")
    val recipeId: String? = null,
    @SerializedName(value = "Quantity")
    val quantity: Double? = null,
    @SerializedName(value = "Unit")
    val unit: String? = null,
    @SerializedName(value = "Ingredient")
    val ingredient: IngredientResponse? = null
)
