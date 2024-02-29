package com.cremasfood.app.di.navigation

import com.cremasfood.app.features.splash.navigation.SplashNavigation
import com.cremasfood.app.features.splash.navigation.SplashNavigationImpl
import com.cremasfood.app.navigation.manager.NavigationManager
import org.koin.dsl.module

val navigationModule = module {
    single {
        NavigationManager(get())
    }

    factory<SplashNavigation> {
        SplashNavigationImpl(navManager = get())
    }
}