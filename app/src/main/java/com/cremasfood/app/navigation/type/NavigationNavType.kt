package com.cremasfood.app.navigation.type

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.cremasfood.app.extensions.string.decode
import com.google.gson.Gson

class NavigationNavType<T : Parcelable>(
    private val type: Class<T>,
    isNullableAllowed: Boolean = false
) : NavType<T>(isNullableAllowed = isNullableAllowed) {

    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): T {
        return Gson().fromJson(value.decode(), type)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }
}