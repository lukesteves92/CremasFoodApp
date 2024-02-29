package com.cremasfood.app.data.remote.mapper

import com.cremasfood.app.data.remote.model.country.CountryResponse
import com.cremasfood.app.data.remote.model.ingredient.IngredientResponse
import com.cremasfood.app.data.remote.model.recipe.RecipeResponse
import com.cremasfood.app.data.remote.model.recipegroup.RecipeGroupResponse
import com.cremasfood.app.data.remote.model.recipeingredient.RecipeIngredientResponse
import com.cremasfood.app.domain.model.country.CountryDomain
import com.cremasfood.app.domain.model.ingredient.IngredientDomain
import com.cremasfood.app.domain.model.recipe.RecipeDomain
import com.cremasfood.app.domain.model.recipegroup.RecipeGroupDomain
import com.cremasfood.app.domain.model.recipeingredient.RecipeIngredientDomain

internal fun CountryResponse.countryResponseToDomain() = CountryDomain(
    id = this.id,
    name = this.name,
    geoCoordinates = this.geoCoordinates
)

internal fun IngredientResponse.ingredientResponseToDomain() = IngredientDomain(
    id = this.id,
    name = this.name
)

internal fun RecipeGroupResponse.recipeGroupResponseToDomain() = RecipeGroupDomain(
    id = this.id,
    name = this.name
)

internal fun RecipeIngredientResponse.recipeIngredientResponseToDomain() = RecipeIngredientDomain(
    id = this.id,
    recipeId = this.recipeId,
    quantity = this.quantity,
    unit = this.unit,
    ingredient = this.ingredient?.ingredientResponseToDomain()
)

internal fun RecipeResponse.recipeResponseToDomain() = RecipeDomain(
    id = this.id,
    title = this.title,
    imageBase64 = this.imageBase64,
    description = this.description,
    country = this.country?.countryResponseToDomain(),
    recipeGroup = this.recipeGroup?.map { recipeGroupOption -> recipeGroupOption.recipeGroupResponseToDomain() },
    recipeIngredient = this.recipeIngredient?.map { recipeIngredientOption -> recipeIngredientOption.recipeIngredientResponseToDomain() }
)