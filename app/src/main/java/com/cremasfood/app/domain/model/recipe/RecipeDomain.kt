package com.cremasfood.app.domain.model.recipe

import android.graphics.Bitmap
import android.os.Parcelable
import com.cremasfood.app.domain.model.country.CountryDomain
import com.cremasfood.app.domain.model.recipegroup.RecipeGroupDomain
import com.cremasfood.app.domain.model.recipeingredient.RecipeIngredientDomain
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class RecipeDomain(
    val id: String? = null,
    val title: String? = null,
    val imageBase64: Bitmap? = null,
    val description: String? = null,
    val country: CountryDomain? = null,
    val recipeGroup: @RawValue List<RecipeGroupDomain>? = null,
    val recipeIngredient: @RawValue List<RecipeIngredientDomain>? = null
) : Parcelable
