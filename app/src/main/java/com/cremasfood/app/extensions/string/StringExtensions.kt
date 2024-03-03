package com.cremasfood.app.extensions.string

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.cremasfood.app.data.remote.model.exception.GenericException
import com.google.gson.Gson
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

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

internal fun String.convertLat() = this.split("|")[0]

internal fun String.convertLong() = this.split("|")[1]

@OptIn(ExperimentalEncodingApi::class)
internal fun String.convertBase64ToBitmap(): Bitmap? {
    return runCatching {
        val byteArray = Base64.Default.decode(this)
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }.getOrElse { null }
}