package com.cremasfood.app.di.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single {
        CoroutineScope(Dispatchers.IO)
    }
}