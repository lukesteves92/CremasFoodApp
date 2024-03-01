package com.cremasfood.app.di.navigation

import com.cremasfood.app.features.details.navigation.DetailsNavigation
import com.cremasfood.app.features.details.navigation.DetailsNavigationImpl
import com.cremasfood.app.features.home.navigation.HomeNavigation
import com.cremasfood.app.features.home.navigation.HomeNavigationImpl
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

    factory<HomeNavigation> {
        HomeNavigationImpl(navManager = get())
    }

    factory<DetailsNavigation> {
        DetailsNavigationImpl(navManager = get())
    }
}