package com.cremasfood.app.data.remote.model.recipe

import com.cremasfood.app.data.remote.model.country.CountryResponse
import com.cremasfood.app.data.remote.model.recipegroup.RecipeGroupResponse
import com.cremasfood.app.data.remote.model.recipeingredient.RecipeIngredientResponse
import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName(value = "Id")
    val id: String? = null,
    @SerializedName(value = "Title")
    val title: String? = null,
    @SerializedName(value = "ImageBase64")
    val imageBase64: String? = null,
    @SerializedName(value = "Description")
    val description: String? = null,
    @SerializedName(value = "Country")
    val country: CountryResponse? = null,
    @SerializedName(value = "RecipeGroup")
    val recipeGroup: List<RecipeGroupResponse>? = null,
    @SerializedName(value = "RecipeIngredient")
    val recipeIngredient: List<RecipeIngredientResponse>? = null
)
