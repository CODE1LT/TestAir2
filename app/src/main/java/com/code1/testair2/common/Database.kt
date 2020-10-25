package com.code1.testair2.common

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.code1.testair2.feature.citieslist.data.local.CitiesDao
import com.code1.testair2.feature.citieslist.data.local.CitiesListEntity
import com.code1.testair2.feature.citieslist.data.local.CityEntity

@Database(
    entities = [
        CityEntity::class,
        CitiesListEntity::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun citiesDao(): CitiesDao
}