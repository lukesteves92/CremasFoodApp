package com.cremasfood.app.domain.model.ingredient

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientDomain(
    val id: String? = null,
    val name: String? = null
): Parcelable
