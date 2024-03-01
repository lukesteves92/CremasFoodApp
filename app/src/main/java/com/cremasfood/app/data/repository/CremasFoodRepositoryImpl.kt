package com.cremasfood.app.data.repository

import com.cremasfood.app.data.remote.mapper.countryResponseToDomain
import com.cremasfood.app.data.remote.mapper.recipeGroupResponseToDomain
import com.cremasfood.app.data.remote.mapper.recipeResponseToDomain
import com.cremasfood.app.data.remote.model.country.CountryResponse
import com.cremasfood.app.data.remote.model.recipe.RecipeResponse
import com.cremasfood.app.data.remote.model.recipegroup.RecipeGroupResponse
import com.cremasfood.app.data.remote.services.responseapi.ResponseApi
import com.cremasfood.app.data.remote.services.service.CremasFoodService
import com.cremasfood.app.data.remote.services.wrapper.RequestWrapper
import com.cremasfood.app.data.utils.exception.CremasFoodException
import com.cremasfood.app.domain.model.country.CountryDomain
import com.cremasfood.app.domain.model.recipe.RecipeDomain
import com.cremasfood.app.domain.model.recipegroup.RecipeGroupDomain
import com.cremasfood.app.domain.repository.CremasFoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Suppress("UNCHECKED_CAST")
class CremasFoodRepositoryImpl(
    private val wrapper: RequestWrapper, private val service: CremasFoodService
) : CremasFoodRepository {
    override fun getRecipeById(id: String): Flow<RecipeDomain> = flow {
        val result = wrapper.wrapper {
            service.getRecipeById(id = id)
        }
        when (result) {
            is ResponseApi.Success -> {
                val wrapperResult = result.data as RecipeResponse
                emit(wrapperResult.recipeResponseToDomain())
            }

            is ResponseApi.ErrorException -> throw result.data as CremasFoodException
        }
    }

    override fun getAllRecipeGroups(): Flow<List<RecipeGroupDomain>> = flow {
        val result = wrapper.wrapper {
            service.getAllRecipeGroups()
        }
        when (result) {
            is ResponseApi.Success -> {
                val wrapperResult = result.data as List<RecipeGroupResponse>
                emit(wrapperResult.map { dataOption -> dataOption.recipeGroupResponseToDomain() })
            }

            is ResponseApi.ErrorException -> throw result.data as CremasFoodException
        }
    }

    override fun getAllCountry(): Flow<List<CountryDomain>> = flow {
        val result = wrapper.wrapper {
            service.getAllCountry()
        }
        when (result) {
            is ResponseApi.Success -> {
                val wrapperResult = result.data as List<CountryResponse>
                emit(wrapperResult.map { dataOption -> dataOption.countryResponseToDomain() })
            }

            is ResponseApi.ErrorException -> throw result.data as CremasFoodException
        }
    }

    override suspend fun getAllRecipes(
        search: String?, recipeGroupId: String?, skip: Int, take: Int
    ): ResponseApi {
        return wrapper.wrapper {
            service.getAllRecipes(
                search = search,
                recipeGroupId = recipeGroupId,
                skip = skip,
                take = take
            )
        }
    }
}