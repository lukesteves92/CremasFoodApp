package com.cremasfood.app.presentation.home

import android.content.Context
import androidx.paging.PagingData
import androidx.paging.map
import com.cremasfood.app.domain.model.recipe.RecipeDomain
import com.cremasfood.app.domain.usecase.recipes.GetAllRecipesUseCase
import com.cremasfood.app.features.home.viewmodel.HomeViewModel
import com.cremasfood.app.main.MainDispatcherRule
import com.cremasfood.app.utils.internet.CheckInternet
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

    private val context: Context = mockk(relaxed = true)
    private val getAllRecipesUseCase: GetAllRecipesUseCase = mockk(relaxed = true)
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(
            getAllRecipesUseCase = getAllRecipesUseCase
        )
    }

    @Test
    fun shouldCheckInternetConnectionFalse() = runTest {

        coEvery {
            CheckInternet.isInternetAvailable(context = context)
        } returns false

        homeViewModel.checkInternetConnection(context = context)

        assert(!homeViewModel.state.value.checkInternet)
    }


    @Test
    fun shouldCheckAllRecipesUseCase() = runTest {

        val searchText = "Brazilian Caipirinha"
        val testData =
            PagingData.from(listOf(RecipeDomain(id = "500", title = "Brazilian Caipirinha")))

        coEvery {
            getAllRecipesUseCase.getAllRecipes(search = searchText)
        } returns flowOf(testData)

        homeViewModel.checkInternetConnection(context = context)

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

        val searchText = "Brazilian Caipirinha"
        val testData = PagingData.from(emptyList<RecipeDomain>())

        coEvery {
            getAllRecipesUseCase.getAllRecipes(search = searchText)
        } returns flowOf(testData)

        homeViewModel.checkInternetConnection(context = context)

        homeViewModel.state.value.recipes.collect { list ->
            list.map { listData ->
                testData.map { testData ->
                    Assert.assertEquals(listData.id, testData.id)
                }
            }
        }
    }
}