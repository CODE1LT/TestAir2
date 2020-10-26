package com.code1.testair2.feature.citieslist.data.local

import javax.inject.Inject

class CityDataSource @Inject constructor(private val citiesDao: CitiesDao) {

    suspend fun addCity(city: CityEntity) {
        citiesDao.addCity(city)
    }

    suspend fun cleanupSearchHistory() {
        citiesDao.cleanupSearchHistory()
    }

    suspend fun queryCities() = citiesDao.queryCities()

}