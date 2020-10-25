package com.code1.testair2.feature.citieslist.domain.usecase

import android.annotation.SuppressLint
import android.text.format.DateFormat
import com.code1.testair2.common.Result
import com.code1.testair2.feature.citieslist.domain.CitiesRepository
import com.code1.testair2.feature.citieslist.domain.model.CitiesListDomainModel
import com.code1.testair2.feature.citieslist.domain.model.CityInlinedDomainModel
import com.code1.testair2.feature.citieslist.domain.model.WeatherDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.*

class GetCitiesListUseCaseImpl(
    private val citiesRepository: CitiesRepository
) : GetCitiesListUseCase {
    override suspend fun invoke(): Flow<Result<List<CityInlinedDomainModel>>> =
        citiesRepository.getCitiesList()
            .map { result ->
                when (result) {
                    is Result.Success -> Result.Success(inlineCityData(result.data))
                    is Result.Error -> Result.Error(result.exception)
                }
            }

    private fun inlineCityData(citiesList: List<CitiesListDomainModel>): List<CityInlinedDomainModel> {

        val citiesListMapped = arrayListOf<CityInlinedDomainModel>()
        citiesList.forEach { citiesListItem ->
            citiesListMapped.add(
                CityInlinedDomainModel(
                    id = citiesListItem.id,
                    name = citiesListItem.name,
                    dt = citiesListItem.dt,
                    dayName = getDayName(citiesListItem.dt),
                    dayNumber = getDayNumber(citiesListItem.dt),
                    temp = citiesListItem.temp?.toInt().toString(),
                    temp_min = citiesListItem.temp_min,
                    temp_max = citiesListItem.temp_max,
                    icon = citiesListItem.icon,
                    description = getDescription(citiesListItem.description)
                )
            )
        }

        return citiesListMapped
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDayName(date: Long?): String {
        return SimpleDateFormat("EEEE").format(getDateObject(date)).toUpperCase(Locale.getDefault())
            .substring(0, 3)
    }

    private fun getDateObject(date: Long?) = Date(date?.times(1000) ?: 0L)

    private fun getDayNumber(date: Long?): String {
        return DateFormat.format("dd", getDateObject(date)).toString()
    }

    private fun getDescription(weatherList: List<WeatherDomainModel>?): String {
        val descriptionsList = weatherList?.map { it.description } as ArrayList
        return descriptionsList.joinToString()
    }

}