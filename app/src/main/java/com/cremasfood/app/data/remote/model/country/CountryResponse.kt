package com.cremasfood.app.data.remote.model.country

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName(value = "id")
    val id: String? = null,
    @SerializedName(value = "name")
    val name: String? = null,
    @SerializedName(value = "geoCoordinates")
    val geoCoordinates: String? = null
)
