package com.code1.testair2.feature.citieslist.domain.usecase

import lt.code1.testair.archextensions.mapSingleResource
import lt.code1.testair.datalayer.cities.CitiesRepository
import lt.code1.testair.datalayer.cities.entities.CitiesListEntity
import lt.code1.testair.datalayer.core.Resource
import lt.code1.testair.domain.RetrieveSingleInteractorWithParams
import com.code1.testair2.feature.citieslist.domain.model.CityInlinedDomainModel
import javax.inject.Inject

class FetchCityInteractor @Inject constructor(
    private val citiesRepository: CitiesRepository,
    private val citiesListMapper: @JvmSuppressWildcards Function1<@JvmSuppressWildcards List<CitiesListEntity>, @JvmSuppressWildcards List<CityInlinedDomainModel>>
) : RetrieveSingleInteractorWithParams<String, Resource<@JvmSuppressWildcards List<CityInlinedDomainModel>>> {

    override fun getSingle(params: String) =
        citiesRepository.fetchCity(params).mapSingleResource(citiesListMapper)
}
