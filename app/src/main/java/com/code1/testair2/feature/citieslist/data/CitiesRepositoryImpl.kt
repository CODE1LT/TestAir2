package com.code1.testair2.feature.citieslist.data

import com.code1.testair2.common.NetworkErrorFormatter
import com.code1.testair2.feature.citieslist.domain.model.CityDomainModel
import com.code1.testair2.common.Result
import com.code1.testair2.feature.citieslist.data.local.CitiesDao
import com.code1.testair2.feature.citieslist.data.local.CitiesListEntity
import com.code1.testair2.feature.citieslist.data.remote.GetCitiesListService
import com.code1.testair2.feature.citieslist.data.remote.GetCityService
import com.code1.testair2.feature.citieslist.data.remote.model.toDomainModel
import com.code1.testair2.feature.citieslist.domain.CitiesRepository
import com.code1.testair2.feature.citieslist.domain.model.CitiesListDomainModel
import com.code1.testair2.feature.citieslist.domain.model.toEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

private const val COUNTRY_CODE = ",LTU"

class CitiesRepositoryImpl @Inject constructor(
    private val networkErrorFormatter: NetworkErrorFormatter,
    private val citiesDao: CitiesDao,

    private val getCityService: GetCityService,
    private val getCitiesListService: GetCitiesListService,
    private val fetchCityRequestMediator: RequestMediator<GetCityResponse>,
    private val fetchCitiesListRequestMediator: RequestMediator<GetCitiesListResponse>,
    private val cityEntityMapper: @JvmSuppressWildcards Function1<GetCityResponse, @JvmSuppressWildcards List<CitiesListEntity>>,
    private val citiesListResponseMapper: @JvmSuppressWildcards Function1<GetCitiesListResponse, @JvmSuppressWildcards List<CitiesListEntity>>,
    private val coroutineContext: CoroutineContext
) : CitiesRepository {

    override suspend fun fetchCity(city: String) : Flow<Result<List<CitiesListDomainModel>>> {
        val result = getCity(city)
        try {
            when (val result = getCity(city)) {
                is Result.Success -> {
                    saveCityId(listOf(result.data.toEntity()))


                    adviceDataSource.addAdvice(result.data)
                    Result.Success(Unit)
                }
                is Result.Error -> result
            }
        } catch (e: Exception) {
            Result.Error(e.also { Timber.e(it) })
        }
    }

    private suspend fun getCity(city: String): Result<CityDomainModel> =
        withContext(Dispatchers.IO) {
            try {
                val result = getCityService.getCity(city + COUNTRY_CODE)
                val resultBody = result.body()

                if (result.isSuccessful && resultBody != null) {
                    Result.Success( resultBody.toDomainModel())
                } else {
                    Timber.e(networkErrorFormatter.createException(result))
                    Result.Error(IOException("Failed to get city"))
                }
            } catch (ex: Exception) {
                Result.Error(ex).also { Timber.e(ex) }
            }
        }


//    override fun fetchCity(city: String) = fetchCityRequestMediator
//        .request {
//            getCityService.getCity(city + COUNTRY_CODE)
//                .unwrap()
//        }
//        .mapSingleResource(cityEntityMapper)
//        .mapSingleResource {
//            saveCityId(it)
//            it
//        }

    private fun saveCityId(citiesList: List<CitiesListEntity>) {
        if (citiesList[0].id != null) {
            CoroutineScope(coroutineContext).launch {
                citiesDao.addCity(
                    CityEntity(
                        rowId = System.currentTimeMillis() / 1000L,
                        name = citiesList[0].name,
                        cityId = citiesList[0].id
                    )
                )

                citiesDao.cleanupSearchHistory()
            }
        }
    }

    override fun getCitiesList() = fetchCitiesListRequestMediator
        .request {
            val citiesIds =
                citiesDao.queryCities()?.map { it.cityId }?.joinToString()?.replace("\\s".toRegex(), "")
            citiesIds?.let { getCitiesListService.getCitiesList(it).unwrap() }
                ?: GetCitiesListResponse(listOf())
        }
        .mapSingleResource(citiesListResponseMapper)

}