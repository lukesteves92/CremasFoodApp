package com.cremasfood.app.data.remote.model.recipeingredient

import com.cremasfood.app.data.remote.model.ingredient.IngredientResponse
import com.google.gson.annotations.SerializedName

data class RecipeIngredientResponse(
    @SerializedName(value = "id")
    val id: String? = null,
    @SerializedName(value = "recipeId")
    val recipeId: String? = null,
    @SerializedName(value = "quantity")
    val quantity: Double? = null,
    @SerializedName(value = "unit")
    val unit: String? = null,
    @SerializedName(value = "ingredient")
    val ingredient: IngredientResponse? = null
)
