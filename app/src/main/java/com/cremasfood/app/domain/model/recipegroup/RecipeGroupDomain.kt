package com.cremasfood.app.domain.model.recipegroup

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeGroupDomain(
    val id: String? = null,
    val name: String? = null
): Parcelable
