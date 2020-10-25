package lt.code1.testair.features.citieslist.interactors

import android.annotation.SuppressLint
import android.text.format.DateFormat
import lt.code1.testair.datalayer.cities.entities.CitiesListEntity
import lt.code1.testair.features.citieslist.data.City
import lt.code1.testair.network.services.cities.pojos.Weather
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CitiesListMapper @Inject constructor() :
    @JvmSuppressWildcards Function1<@JvmSuppressWildcards List<CitiesListEntity>, @JvmSuppressWildcards List<City>> {

    override fun invoke(citiesList: List<CitiesListEntity>): List<City> {

        val citiesListMapped = arrayListOf<City>()
        citiesList.forEach { citiesListItem ->
            citiesListMapped.add(
                City(
                    id = citiesListItem.id,
                    name = citiesListItem.name,
                    dt = citiesListItem.dt,
                    dayName = getDayName(citiesListItem.dt),
                    dayNumber = getDayNumber(citiesListItem.dt),
                    temp = citiesListItem.temp?.toInt().toString(),
                    temp_min = citiesListItem.temp_min,
                    temp_max = citiesListItem.temp_max,
                    icon = citiesListItem.icon,
                    description = getDescription(citiesListItem.description)
                )
            )
        }

        return citiesListMapped
    }

    private fun getDescription(weatherList: List<Weather>?): String {
        val descriptionsList = weatherList?.map { it.description } as ArrayList
        return descriptionsList.joinToString()
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDayName(date: Long?): String {
        return SimpleDateFormat("EEEE").format(getDateObject(date)).toUpperCase(Locale.getDefault())
            .substring(0, 3)
    }

    private fun getDayNumber(date: Long?): String {
        return DateFormat.format("dd", getDateObject(date)).toString()
    }

    private fun getDateObject(date: Long?) = Date(date?.times(1000) ?: 0L)

}