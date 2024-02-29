package com.cremasfood.app.data.utils.exception

import com.cremasfood.app.utils.Constants.Text.EMPTY_STRING_DEFAULT

sealed class CremasFoodException(
    override val message: String? = EMPTY_STRING_DEFAULT,
    val code: String? = EMPTY_STRING_DEFAULT
) : Throwable() {
    data object ErrorNetworkException : CremasFoodException()
    class DefaultError(message: String, code: String) : CremasFoodException(message, code)
}
