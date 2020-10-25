package lt.code1.testair.features.citieslist.interactors

import lt.code1.testair.archextensions.mapSingleResource
import lt.code1.testair.datalayer.cities.CitiesRepository
import lt.code1.testair.datalayer.cities.entities.CitiesListEntity
import lt.code1.testair.datalayer.core.Resource
import lt.code1.testair.domain.RetrieveSingleInteractorWithParams
import lt.code1.testair.features.citieslist.data.City
import javax.inject.Inject

class FetchCityInteractor @Inject constructor(
    private val citiesRepository: CitiesRepository,
    private val citiesListMapper: @JvmSuppressWildcards Function1<@JvmSuppressWildcards List<CitiesListEntity>, @JvmSuppressWildcards List<City>>
) : RetrieveSingleInteractorWithParams<String, Resource<@JvmSuppressWildcards List<City>>> {

    override fun getSingle(params: String) =
        citiesRepository.fetchCity(params).mapSingleResource(citiesListMapper)
}
