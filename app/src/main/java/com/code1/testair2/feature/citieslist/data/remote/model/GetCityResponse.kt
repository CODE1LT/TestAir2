package com.code1.testair2.feature.citieslist.data.remote.model

import com.code1.testair2.feature.citieslist.data.local.CitiesListEntity
import com.code1.testair2.feature.citieslist.domain.model.CitiesListDomainModel
import com.code1.testair2.feature.citieslist.domain.model.WeatherDomainModel
import com.google.gson.annotations.SerializedName

data class GetCityResponse(
    @SerializedName("id") val id: Long?,
    @SerializedName("name") val name: String?,
    @SerializedName("dt") val dt: Long?,
    @SerializedName("main") val main: Main?,
    @SerializedName("weather") val weather: List<Weather>?
)

data class Main (
    @SerializedName("temp") val temp: Float?,
    @SerializedName("temp_min") val temp_min: Float?,
    @SerializedName("temp_max") val temp_max: Float?
)

data class Weather (
    @SerializedName("description") val description: String?,
    @SerializedName("icon") val icon: String?
)

fun GetCityResponse.toDomainModel() = CitiesListDomainModel(
    id = id,
    name = name,
    dt = dt,
    temp = main?.temp,
    temp_min = main?.temp_min,
    temp_max = main?.temp_max,
    icon = weather?.get(0)?.icon,
    description = weather?.map {
        WeatherDomainModel(it.description, it.icon)
    }
)

fun GetCityResponse.toEntity() =             CitiesListEntity(
    id = id,
    name = name,
    dt = dt,
    temp = main?.temp,
    temp_min = main?.temp_min,
    temp_max = main?.temp_max,
    icon = weather?.get(0)?.icon,
    description = weather
)