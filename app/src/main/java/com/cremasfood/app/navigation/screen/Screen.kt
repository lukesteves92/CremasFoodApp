package com.cremasfood.app.navigation.screen

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.cremasfood.app.domain.model.recipe.RecipeDomain
import com.cremasfood.app.navigation.screen.Screen.Details.toGson
import com.cremasfood.app.navigation.type.NavigationNavType
import com.cremasfood.app.utils.Constants.Keys.DETAILS_KEY
import com.cremasfood.app.utils.Constants.Keys.SEARCH_KEY
import com.cremasfood.app.utils.Constants.Text.EMPTY_STRING_DEFAULT
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

    data object Search : Screen(route = "Search") {
        val arguments = listOf(
            navArgument(SEARCH_KEY) {
                type = NavType.StringType
                defaultValue = EMPTY_STRING_DEFAULT
                nullable = false
            }
        )

        fun createRoute(searchText: String) = route.plus("/${searchText}")
    }
}