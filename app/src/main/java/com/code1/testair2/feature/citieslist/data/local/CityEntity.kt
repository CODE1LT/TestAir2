package com.code1.testair2.feature.citieslist.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey(autoGenerate = false)
    val rowId: Long,
    val name: String?,
    val cityId: Long?
)