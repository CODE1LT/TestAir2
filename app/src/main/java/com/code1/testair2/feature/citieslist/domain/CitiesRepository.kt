package com.code1.testair2.feature.citieslist.domain

import com.code1.testair2.common.Result
import com.code1.testair2.feature.citieslist.domain.model.CitiesListDomainModel
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    fun fetchCity(city: String): Flow<Result<List<CitiesListDomainModel>>>
    fun getCitiesList(): Flow<Result<List<CitiesListDomainModel>>>
}