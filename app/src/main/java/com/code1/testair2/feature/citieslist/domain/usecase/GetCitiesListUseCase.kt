package com.code1.testair2.feature.citieslist.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.code1.testair2.common.Result
import com.code1.testair2.feature.citieslist.domain.model.CityInlinedDomainModel

interface GetCitiesListUseCase {
    suspend operator fun invoke(): Flow<Result<List<CityInlinedDomainModel>>>
}