package com.cremasfood.app.data.remote.services.responseapi

sealed class ResponseApi{
    class Success(var data: Any?) : ResponseApi()
    class ErrorException(var data: Any?) : ResponseApi()
}