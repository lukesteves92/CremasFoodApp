package com.cremasfood.app.data.remote.services.wrapper

import com.cremasfood.app.data.remote.services.responseapi.ResponseApi
import com.cremasfood.app.data.utils.exception.CremasFoodException
import com.cremasfood.app.extensions.string.containsTagHtml
import com.cremasfood.app.extensions.string.toDefaultError
import com.cremasfood.app.utils.Constants.Text.EMPTY_STRING_DEFAULT
import retrofit2.Response
import java.io.IOException
import javax.net.ssl.HttpsURLConnection

class RequestWrapperImpl: RequestWrapper {

    override suspend fun <T : Any> wrapper(call: suspend () -> Response<T>): ResponseApi<T> {
        return try {
            val response = call.invoke()
            val errorBody = response.errorBody()?.string() ?: EMPTY_STRING_DEFAULT

            when {
                response.isSuccessful -> {

                    ResponseApi.Success(
                        data = response.body()
                    )
                }

                response.code() == HttpsURLConnection.HTTP_FORBIDDEN && errorBody.containsTagHtml() -> {
                    ResponseApi.ErrorException(
                        CremasFoodException.DefaultError(
                            errorBody,
                            "${response.code()}"
                        )
                    )
                }

                else -> {
                    val error= errorBody.toDefaultError()
                    ResponseApi.ErrorException(
                        CremasFoodException.DefaultError(
                            message = error?.message ?: EMPTY_STRING_DEFAULT,
                            code = error?.code ?: EMPTY_STRING_DEFAULT
                        )
                    )
                }
            }
        } catch (exception: IOException) {
            ResponseApi.ErrorException(CremasFoodException.ErrorNetworkException)
        } catch (exception: CremasFoodException) {
            ResponseApi.ErrorException(exception)
        }
    }
}