package com.cremasfood.app.data.remote.model.recipegroup

import com.google.gson.annotations.SerializedName

data class RecipeGroupResponse(
    @SerializedName(value = "id")
    val id: String? = null,
    @SerializedName(value = "name")
    val name: String? = null
)
