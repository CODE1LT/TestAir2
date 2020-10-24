package com.code1.testair2.common

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) {
            null
        } else {
            Date(value)
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }


    @TypeConverter
    fun toDate(dateString: String?): LocalDateTime? {
        return if (dateString == null) {
            null
        } else {
            LocalDateTime.parse(dateString)
        }
    }

    @TypeConverter
    fun toInstantString(date: Instant?): String? = date?.toString()

    @TypeConverter
    fun toInstant(dateString: String?): Instant? {
        return if (dateString == null) {
            null
        } else {
            Instant.parse(dateString)
        }
    }

    @TypeConverter
    fun toOffsetDateTimeString(date: OffsetDateTime?): String? = date?.toString()

    @TypeConverter
    fun toOffsetDateTime(dateString: String?): OffsetDateTime? {
        return if (dateString == null) {
            null
        } else {
            OffsetDateTime.parse(dateString)
        }
    }

    @TypeConverter
    fun toDateString(date: LocalDateTime?): String? = date?.toString()

    @TypeConverter
    fun fromBigDecimal(bigDecimal: BigDecimal?): String? = bigDecimal?.toString()

    @TypeConverter
    fun toBigDecimal(string: String?): BigDecimal? = string?.toBigDecimal()

    @Suppress("NOTHING_TO_INLINE")
    private inline fun <T : Enum<T>> T.toInt(): Int = this.ordinal

    private inline fun <reified T : Enum<T>> Int.toEnum(): T = enumValues<T>()[this]

}