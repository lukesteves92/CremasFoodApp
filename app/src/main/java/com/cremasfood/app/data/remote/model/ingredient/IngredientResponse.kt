package com.cremasfood.app.data.remote.model.ingredient

import com.google.gson.annotations.SerializedName

data class IngredientResponse(
    @SerializedName(value = "id")
    val id: String? = null,
    @SerializedName(value = "name")
    val name: String? = null
)