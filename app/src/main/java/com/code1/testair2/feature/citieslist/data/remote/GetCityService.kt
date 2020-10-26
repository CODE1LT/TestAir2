package com.code1.testair2.feature.citieslist.data.remote

import com.code1.testair2.feature.citieslist.data.remote.model.GetCityResponse
import com.code1.testair2.injection.remotemodules.APPID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val CITIES_WEATHER_PATH = "data/2.5/weather"
private const val UNITS = "metric"

interface GetCityService {

    @GET(CITIES_WEATHER_PATH)
    suspend fun getCity(
        @Query("q") cityAndCountry: String,
        @Query("units") units: String = UNITS,
        @Query("appid") apiKey: String = APPID
    ): Response<GetCityResponse>
}