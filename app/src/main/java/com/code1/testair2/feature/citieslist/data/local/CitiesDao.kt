package com.code1.testair2.feature.citieslist.data.local

import androidx.room.*

private const val CITIES_SEARCH_HISTORY_SIZE_LIMIT = 5

@Dao
abstract class CitiesDao {

    @Query("SELECT * FROM cities ORDER BY rowId DESC")
    abstract suspend fun queryCities(): List<CityEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addCity(city: CityEntity)

    @Query("DELETE FROM cities WHERE rowId NOT IN (SELECT rowId FROM cities ORDER BY rowId DESC LIMIT $CITIES_SEARCH_HISTORY_SIZE_LIMIT)")
    abstract suspend fun cleanupSearchHistory()

}