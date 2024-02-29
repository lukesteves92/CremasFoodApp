package com.cremasfood.app.di.data

import com.cremasfood.app.BuildConfig
import com.cremasfood.app.data.remote.services.retrofit.RetrofitConfig
import com.cremasfood.app.data.remote.services.service.CremasFoodService
import com.cremasfood.app.data.remote.services.wrapper.RequestWrapper
import com.cremasfood.app.data.remote.services.wrapper.RequestWrapperImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single {
        CoroutineScope(Dispatchers.IO)
    }

    single {
        RetrofitConfig.provideOkHttpClient()
    }

    single<CremasFoodService> {
        RetrofitConfig.createService(get(), BuildConfig.URL_BASE)
    }

    single<RequestWrapper> {
        RequestWrapperImpl()
    }
}