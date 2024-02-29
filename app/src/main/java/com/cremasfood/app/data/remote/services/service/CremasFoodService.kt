package com.cremasfood.app.data.remote.services.service

import com.cremasfood.app.data.remote.model.country.CountryResponse
import com.cremasfood.app.data.remote.model.recipe.RecipeResponse
import com.cremasfood.app.data.remote.model.recipegroup.RecipeGroupResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CremasFoodService {

    @GET("/recipe")
    suspend fun getAllRecipes(
        @Query("search") search: String? = null,
        @Query("recipeGroupId") recipeGroupId: String? = null,
        @Query("skip") skip: Int,
        @Query("take") take: Int
    ): Response<List<RecipeResponse>>

    @GET("/recipe/{id}")
    suspend fun getRecipeById(
        @Path("id") id: String,
    ): Response<RecipeResponse>

    @GET("/recipe/Group")
    suspend fun getAllRecipeGroups(): Response<List<RecipeGroupResponse>>

    @GET("/country")
    suspend fun getAllCountry(): Response<List<CountryResponse>>
}