package com.code1.testair2.feature.citieslist.domain.model


data class CitiesListDomainModel(
    val id: Long?,
    val name: String?,
    val dt: Long?,
    val temp: Float?,
    val temp_min: Float?,
    val temp_max: Float?,
    val icon: String?,
    val description: List<WeatherDomainModel>?
)