package com.code1.testair2.feature.citieslist.data

import com.code1.testair2.common.Result
import com.code1.testair2.extensions.unwrap
import com.code1.testair2.feature.citieslist.data.local.CitiesListEntity
import com.code1.testair2.feature.citieslist.data.local.CityDataSource
import com.code1.testair2.feature.citieslist.data.local.CityEntity
import com.code1.testair2.feature.citieslist.data.remote.GetCitiesListService
import com.code1.testair2.feature.citieslist.data.remote.GetCityService
import com.code1.testair2.feature.citieslist.data.remote.model.toDomainModel
import com.code1.testair2.feature.citieslist.data.remote.model.toEntity
import com.code1.testair2.feature.citieslist.domain.CitiesRepository
import com.code1.testair2.feature.citieslist.domain.model.CitiesListDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

private const val COUNTRY_CODE = ",LTU"

class CitiesRepositoryImpl @Inject constructor(
    private val cityDataSource: CityDataSource,
    private val getCityService: GetCityService,
    private val getCitiesListService: GetCitiesListService
) : CitiesRepository {

    override fun fetchCity(city: String): Flow<Result<List<CitiesListDomainModel>>> {
        return flow<Result<List<CitiesListDomainModel>>> {
            val response = getCityService.getCity(city + COUNTRY_CODE).unwrap()
            saveCityId(listOf(response.toEntity()))
            cityDataSource.cleanupSearchHistory()
            val result = listOf(response.toDomainModel())
            emit(Result.Success(result))
        }.catch { throwable ->
            Timber.e(throwable)
            emit(Result.Error(Exception(throwable)))
        }
    }

    private suspend fun saveCityId(citiesList: List<CitiesListEntity>) {
        if (citiesList[0].id != null) {
            cityDataSource.addCity(
                CityEntity(
                    rowId = System.currentTimeMillis() / 1000L,
                    name = citiesList[0].name,
                    cityId = citiesList[0].id
                )
            )
        }
    }

    override fun getCitiesList(): Flow<Result<List<CitiesListDomainModel>>> {
        return flow<Result<List<CitiesListDomainModel>>> {
            val citiesIds =
                cityDataSource.queryCities()?.map { it.cityId }?.joinToString()
                    ?.replace("\\s".toRegex(), "")
            val citiesList = mutableListOf<CitiesListDomainModel>()
            if (citiesIds != null) {
                val response = getCitiesListService.getCitiesList(citiesIds).unwrap()
                response.list?.forEach { citiesList.add(it.toDomainModel()) }
            }
            emit(Result.Success(citiesList))
        }.catch { throwable ->
            Timber.e(throwable)
            emit(Result.Error(Exception(throwable)))
        }
    }
}