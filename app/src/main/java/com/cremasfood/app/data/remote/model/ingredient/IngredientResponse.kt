package com.cremasfood.app.data.remote.model.ingredient

import com.google.gson.annotations.SerializedName

data class IngredientResponse(
    @SerializedName(value = "Id")
    val id: String? = null,
    @SerializedName(value = "Name")
    val name: String? = null
)