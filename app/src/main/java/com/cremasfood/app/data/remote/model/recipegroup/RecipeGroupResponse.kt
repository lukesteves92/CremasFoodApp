package com.cremasfood.app.data.remote.model.recipegroup

import com.google.gson.annotations.SerializedName

data class RecipeGroupResponse(
    @SerializedName(value = "Id")
    val id: String? = null,
    @SerializedName(value = "Name")
    val name: String? = null
)
