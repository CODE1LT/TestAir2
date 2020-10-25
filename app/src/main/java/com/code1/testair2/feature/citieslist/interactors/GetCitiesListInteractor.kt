package lt.code1.testair.features.citieslist.interactors

import lt.code1.testair.archextensions.mapSingleResource
import lt.code1.testair.datalayer.cities.CitiesRepository
import lt.code1.testair.datalayer.cities.entities.CitiesListEntity
import lt.code1.testair.datalayer.core.Resource
import lt.code1.testair.domain.RetrieveSingleInteractor
import lt.code1.testair.features.citieslist.data.City
import javax.inject.Inject

class GetCitiesListInteractor @Inject constructor(
    private val citiesRepository: CitiesRepository,
    private val citiesListMapper: @JvmSuppressWildcards Function1<@JvmSuppressWildcards List<CitiesListEntity>, @JvmSuppressWildcards List<City>>
) : RetrieveSingleInteractor<Resource<@JvmSuppressWildcards List<City>>> {

    override fun getSingle() =
        citiesRepository.getCitiesList().mapSingleResource(citiesListMapper)
}
