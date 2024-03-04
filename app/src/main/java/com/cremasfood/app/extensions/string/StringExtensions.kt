package com.cremasfood.app.extensions.string

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.cremasfood.app.data.remote.model.exception.GenericException
import com.google.gson.Gson
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

internal fun String.convertLat() = this.split("|")[0]

internal fun String.convertLong() = this.split("|")[1]

internal fun String?.convertBase64ToBitmap(): Bitmap? {
   return runCatching {
       val decodedBytes = Base64.decode(this, Base64.DEFAULT)
       BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
   }.getOrNull()
}