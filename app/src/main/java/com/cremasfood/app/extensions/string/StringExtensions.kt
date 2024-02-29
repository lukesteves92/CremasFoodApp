package com.cremasfood.app.extensions.string

import com.cremasfood.app.data.remote.model.GenericException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

fun String.decode(): String = URLDecoder.decode(this, StandardCharsets.UTF_8.toString())

internal fun String?.containsTagHtml() =
    (this != null && this.contains("html"))

internal fun String?.toDefaultError() = try {
    Gson().fromJson(
        this,
        GenericException::class.java
    )
} catch (e: Exception) {
    GenericException(code = "999", message = "generic error")
}