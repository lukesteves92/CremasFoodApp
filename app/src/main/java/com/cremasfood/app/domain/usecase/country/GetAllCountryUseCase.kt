package com.cremasfood.app.domain.usecase.country

import com.cremasfood.app.domain.model.country.CountryDomain
import com.cremasfood.app.domain.repository.CremasFoodRepository
import kotlinx.coroutines.flow.Flow

class GetAllCountryUseCase(
    private val repository: CremasFoodRepository
) {
    fun getAllCountry(): Flow<List<CountryDomain>> = repository.getAllCountry()
}