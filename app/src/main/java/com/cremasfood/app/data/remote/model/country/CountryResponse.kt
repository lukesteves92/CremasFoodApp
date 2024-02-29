package com.cremasfood.app.data.remote.model.country

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName(value = "Id")
    val id: String? = null,
    @SerializedName(value = "Name")
    val name: String? = null,
    @SerializedName(value = "GeoCoordinates")
    val geoCoordinates: String? = null
)
