package com.cremasfood.app.data.remote.model.recipe

import com.cremasfood.app.data.remote.model.country.CountryResponse
import com.cremasfood.app.data.remote.model.recipegroup.RecipeGroupResponse
import com.cremasfood.app.data.remote.model.recipeingredient.RecipeIngredientResponse
import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName(value = "id")
    val id: String? = null,
    @SerializedName(value = "title")
    val title: String? = null,
    @SerializedName(value = "imageBase64")
    val imageBase64: String? = null,
    @SerializedName(value = "description")
    val description: String? = null,
    @SerializedName(value = "country")
    val country: CountryResponse? = null,
    @SerializedName(value = "group")
    val recipeGroup: List<RecipeGroupResponse>? = null,
    @SerializedName(value = "ingredients")
    val recipeIngredient: List<RecipeIngredientResponse>? = null
)
