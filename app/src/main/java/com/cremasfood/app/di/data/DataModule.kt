package com.cremasfood.app.di.data

import com.cremasfood.app.BuildConfig
import com.cremasfood.app.data.remote.services.retrofit.RetrofitConfig
import com.cremasfood.app.data.remote.services.service.CremasFoodService
import com.cremasfood.app.data.remote.services.wrapper.RequestWrapper
import com.cremasfood.app.data.remote.services.wrapper.RequestWrapperImpl
import com.cremasfood.app.data.repository.CremasFoodRepositoryImpl
import com.cremasfood.app.domain.repository.CremasFoodRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    factory {
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

    factory<CremasFoodRepository> {
        CremasFoodRepositoryImpl(
            wrapper = get(),
            service = get()
        )
    }
}