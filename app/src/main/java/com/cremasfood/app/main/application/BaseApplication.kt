package com.cremasfood.app.main.application

import android.app.Application
import com.cremasfood.app.di.data.dataModule
import com.cremasfood.app.di.navigation.navigationModule
import com.cremasfood.app.di.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    navigationModule,
                    presentationModule,
                    dataModule
                )
            )
        }
    }
}