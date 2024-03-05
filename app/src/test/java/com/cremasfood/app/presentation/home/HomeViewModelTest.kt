package com.cremasfood.app.presentation.home

import androidx.paging.PagingData
import androidx.paging.map
import com.cremasfood.app.domain.model.recipe.RecipeDomain
import com.cremasfood.app.domain.usecase.recipes.GetAllRecipesUseCase
import com.cremasfood.app.features.home.viewmodel.HomeViewModel
import com.cremasfood.app.main.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getAllRecipesUseCase: GetAllRecipesUseCase = mockk(relaxed = true)
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(
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

        homeViewModel.getAllRecipesUseCase()

        homeViewModel.state.value.recipes.collect { list ->
            list.map { listData ->
                testData.map { testData ->
                    Assert.assertEquals(listData.id, testData.id)
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

        homeViewModel.getAllRecipesUseCase()

        homeViewModel.state.value.recipes.collect { list ->
            list.map { listData ->
                testData.map { testData ->
                    Assert.assertEquals(listData.id, testData.id)
                }
            }
        }
    }

    companion object {
        private const val KEY_SEARCH_TEXT = "Brazilian Caipirinha"
        private const val KEY_ID_RECIPE = "500"
    }
}