package com.cremasfood.app.data.utils.singleorthrow

import com.cremasfood.app.data.utils.exception.CremasFoodException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single

suspend inline fun <T> Flow<T>.singleOrThrow(
    success: ((T) -> Unit),
    error: ((CremasFoodException) -> Unit)
) {
    try {
        success.invoke(single())
    } catch (e: CremasFoodException) {
        error.invoke(e)
    }
}