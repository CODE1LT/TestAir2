package com.code1.testair2.feature.citieslist.data.remote.model

import com.google.gson.annotations.SerializedName

data class GetCitiesListResponse(
    @SerializedName("list")
    val list: List<GetCityResponse>?
)