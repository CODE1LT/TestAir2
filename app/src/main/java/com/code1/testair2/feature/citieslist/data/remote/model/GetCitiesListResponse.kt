package com.code1.testair2.feature.citieslist.data.remote.model

import com.code1.testair2.feature.citieslist.domain.model.CitiesListDomainModel
import com.google.gson.annotations.SerializedName

data class GetCitiesListResponse(
    @SerializedName("list")
    val list: List<GetCityResponse>?
)

fun GetCitiesListResponse.toDomainModel() = CitiesListDomainModel(
    list = list?.map { it.toDomainModel() }
)