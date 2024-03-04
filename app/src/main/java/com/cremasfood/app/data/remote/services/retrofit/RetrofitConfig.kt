package com.cremasfood.app.data.remote.services.retrofit

import com.cremasfood.app.BuildConfig
import com.cremasfood.app.utils.Constants.Text.EMPTY_STRING_DEFAULT
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {

    inline fun <reified T> createService(
        okHttpClient: OkHttpClient,
        url: String = EMPTY_STRING_DEFAULT
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create()

    }

    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.writeTimeout(10, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(logInterceptor)
        }
//        builder.addInterceptor(AddInterceptorHeader())
        builder.addInterceptor(AddInterceptorQuery())

        return builder.build()
    }

    class AddInterceptorHeader : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val original: Request.Builder = request
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Content-Length", "1158")
                .addHeader("Accept", "application/json")
                .method(request.method, request.body)
            return chain.proceed(original.build())
        }
    }

    class AddInterceptorQuery : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val url = chain.request().url.newBuilder()
                .build()
            val newRequest = chain.request().newBuilder().url(url).build()
            return chain.proceed(newRequest)
        }
    }
}