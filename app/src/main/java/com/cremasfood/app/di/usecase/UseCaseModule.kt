package com.cremasfood.app.di.usecase

import com.cremasfood.app.domain.usecase.country.GetAllCountryUseCase
import com.cremasfood.app.domain.usecase.recipebyid.GetRecipeByIdUseCase
import com.cremasfood.app.domain.usecase.recipegroups.GetAllRecipeGroupsUseCase
import com.cremasfood.app.domain.usecase.recipes.GetAllRecipesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetAllCountryUseCase(repository = get())
    }
    factory {
        GetRecipeByIdUseCase(repository = get())
    }
    factory {
        GetAllRecipeGroupsUseCase(repository = get())
    }
    factory {
        GetAllRecipesUseCase(repository = get())
    }
}