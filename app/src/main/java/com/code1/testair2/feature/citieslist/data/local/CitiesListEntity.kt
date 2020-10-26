package com.code1.testair2.feature.citieslist.data.local

import com.code1.testair2.feature.citieslist.data.remote.model.Weather

data class CitiesListEntity(
    val id: Long?,
    val name: String?,
    val dt: Long?,
    val temp: Float?,
    val temp_min: Float?,
    val temp_max: Float?,
    val icon: String?,
    val description: List<Weather>?
)