package com.cremasfood.app.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cremasfood.app.data.remote.mapper.recipeResponseToDomain
import com.cremasfood.app.data.remote.model.recipe.RecipeResponse
import com.cremasfood.app.data.remote.services.responseapi.ResponseApi
import com.cremasfood.app.data.utils.exception.CremasFoodException
import com.cremasfood.app.domain.model.recipe.RecipeDomain
import com.cremasfood.app.domain.repository.CremasFoodRepository
import com.cremasfood.app.utils.Constants.Numbers.KEY_NUMBER_ONE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Suppress("UNCHECKED_CAST")
class RecipesPagingSource(
    private val repository: CremasFoodRepository,
    private val search: String? = null,
    private val recipeGroupId: String? = null,
    private val take: Int
) : PagingSource<Int, RecipeDomain>() {

    override fun getRefreshKey(state: PagingState<Int, RecipeDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(KEY_NUMBER_ONE)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(KEY_NUMBER_ONE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipeDomain> {
        return try {
            val page = params.key ?: KEY_NUMBER_ONE

            withContext(Dispatchers.IO) {
                var result: List<RecipeDomain> = listOf()
                when (val response = repository.getAllRecipes(
                    search = search,
                    recipeGroupId = recipeGroupId,
                    skip = page,
                    take = take
                )) {
                    is ResponseApi.Success -> {
                        val data = response.data as List<RecipeResponse>
                        result = data.map { dataList -> dataList.recipeResponseToDomain() }
                    }

                    is ResponseApi.ErrorException -> {
                        throw response.data as CremasFoodException
                    }
                }

                LoadResult.Page(
                    data = result,
                    prevKey = if (page == KEY_NUMBER_ONE) null else page.minus(KEY_NUMBER_ONE),
                    nextKey = if (result.isEmpty()) null else page.plus(KEY_NUMBER_ONE)
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}