package com.cremasfood.app.data.remote.services.responseapi

import com.cremasfood.app.data.utils.exception.CremasFoodException

sealed class ResponseApi<out T> {
    class Success<T>(var data: T?) : ResponseApi<T>()
    class ErrorException(var errorException: CremasFoodException) : ResponseApi<Nothing>()

}