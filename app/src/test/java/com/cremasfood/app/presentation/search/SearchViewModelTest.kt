package com.cremasfood.app.presentation.search

import androidx.paging.PagingData
import androidx.paging.map
import com.cremasfood.app.domain.model.recipe.RecipeDomain
import com.cremasfood.app.domain.usecase.recipes.GetAllRecipesUseCase
import com.cremasfood.app.features.search.viewmodel.SearchViewModel
import com.cremasfood.app.main.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getAllRecipesUseCase: GetAllRecipesUseCase = mockk(relaxed = true)
    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setup() {
        searchViewModel = SearchViewModel(
            getAllRecipesUseCase = getAllRecipesUseCase
        )
    }

    @Test
    fun shouldCheckAllRecipesUseCase() = runTest {

        val searchText = KEY_SEARCH_TEXT
        val testData =
            PagingData.from(listOf(RecipeDomain(id = KEY_ID_RECIPE, title = KEY_SEARCH_TEXT)))

        coEvery {
            getAllRecipesUseCase.getAllRecipes(search = searchText)
        } returns flowOf(testData)

        searchViewModel.dispatchViewAction(searchText = searchText)

        searchViewModel.state.value.recipes.collect { list ->
            list.map { listData ->
                testData.map { testData ->
                    assertEquals(listData.id, testData.id)
                }
            }
        }
    }

    @Test
    fun shouldCheckAllRecipesUseCaseEmptyList() = runTest {

        val searchText = KEY_SEARCH_TEXT
        val testData = PagingData.from(emptyList<RecipeDomain>())

        coEvery {
            getAllRecipesUseCase.getAllRecipes(search = searchText)
        } returns flowOf(testData)

        searchViewModel.dispatchViewAction(searchText = searchText)

        searchViewModel.state.value.recipes.collect { list ->
            list.map { listData ->
                testData.map { testData ->
                    assertEquals(listData.id, testData.id)
                }
            }
        }
    }

    companion object {
        private const val KEY_SEARCH_TEXT = "Brazilian Caipirinha"
        private const val KEY_ID_RECIPE = "500"
    }
}