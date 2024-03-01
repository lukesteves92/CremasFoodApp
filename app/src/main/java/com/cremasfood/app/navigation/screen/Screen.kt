package com.cremasfood.app.navigation.screen

import androidx.navigation.navArgument
import com.cremasfood.app.domain.model.recipe.RecipeDomain
import com.cremasfood.app.navigation.type.NavigationNavType
import com.cremasfood.app.utils.Constants.Keys.DETAILS_KEY
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {

    private val gson = Gson()
    fun Any?.toGson(): String =
        URLEncoder.encode(gson.toJson(this), StandardCharsets.UTF_8.toString())

    data object Splash : Screen(route = "splash")
    data object Home : Screen(route = "home")
    data object Details : Screen(route = "Details") {
        val arguments = listOf(
            navArgument(DETAILS_KEY) {
                type = NavigationNavType(RecipeDomain::class.java)
            }
        )

        fun createRoute(recipe: RecipeDomain?) = route.plus("/${recipe.toGson()}")
    }
}