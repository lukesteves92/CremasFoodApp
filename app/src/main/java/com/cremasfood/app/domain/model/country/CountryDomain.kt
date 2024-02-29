package com.cremasfood.app.domain.model.country

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryDomain(
    val id: String? = null,
    val name: String? = null,
    val geoCoordinates: String? = null
): Parcelable
