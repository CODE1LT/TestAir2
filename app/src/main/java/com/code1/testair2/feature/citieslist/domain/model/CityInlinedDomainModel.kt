package com.code1.testair2.feature.citieslist.domain.model

data class CityInlinedDomainModel(
    val id: Long?,
    val name: String?,
    val dt: Long?,
    val dayName: String?,
    val dayNumber: String?,
    val temp: String?,
    val temp_min: Float?,
    val temp_max: Float?,
    val icon: String?,
    val description: String?
)