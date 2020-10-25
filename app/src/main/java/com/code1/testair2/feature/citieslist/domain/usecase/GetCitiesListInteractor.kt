package com.code1.testair2.feature.citieslist.domain.usecase

import com.code1.testair2.feature.citieslist.domain.model.CityInlinedDomainModel
import javax.inject.Inject

class GetCitiesListInteractor @Inject constructor(
    private val citiesRepository: CitiesRepository,
    private val citiesListMapper: @JvmSuppressWildcards Function1<@JvmSuppressWildcards List<CitiesListEntity>, @JvmSuppressWildcards List<CityInlinedDomainModel>>
) : RetrieveSingleInteractor<Resource<@JvmSuppressWildcards List<CityInlinedDomainModel>>> {

    override fun getSingle() =
        citiesRepository.getCitiesList().mapSingleResource(citiesListMapper)
}
