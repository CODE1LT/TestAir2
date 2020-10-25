package com.code1.testair2.feature.citieslist.domain.model

import com.code1.testair2.feature.citieslist.data.local.CitiesListEntity
import com.code1.testair2.feature.citieslist.data.remote.model.Weather

data class CityDomainModel(
    val id: Long?,
    val name: String?,
    val dt: Long?,
    val main: MainDomainModel?,
    val weather: List<WeatherDomainModel>?
)

data class MainDomainModel(
    val temp: Float?,
    val temp_min: Float?,
    val temp_max: Float?
)

data class WeatherDomainModel(
    val description: String?,
    val icon: String?
)

fun CityDomainModel.toEntity() = CitiesListEntity(
    id = id,
    name = name,
    dt = dt,
    temp = main?.temp,
    temp_min = main?.temp_min,
    temp_max = main?.temp_max,
    icon = weather?.get(0)?.icon,
    description = weather?.map {
        Weather(it.description, it.icon)
    }
)

