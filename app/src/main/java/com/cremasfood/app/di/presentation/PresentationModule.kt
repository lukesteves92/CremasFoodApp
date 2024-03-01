package com.cremasfood.app.di.presentation

import com.cremasfood.app.features.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel(
        getAllRecipesUseCase = get()
    ) }
}