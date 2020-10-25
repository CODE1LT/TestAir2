package lt.code1.testair.features.citieslist.list

import android.content.Context
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import lt.code1.testair.databinding.ItemCityBinding
import com.code1.testair2.feature.citieslist.domain.model.CityInlinedDomainModel
import lt.code1.testair.network.BASE_WEATHER_ICON_API_URL
import timber.log.Timber

private const val WEATHER_ICON_TYPE = "@2x.png"
private const val LOWEST_TEMPERATURE_BOUND = 10
private const val MID_TEMPERATURE_BOUND_TOP = 20
private const val MID_TEMPERATURE_BOUND_BOTTOM = 10

class CityItemViewHolder(
    private val context: Context,
    private val binding: ItemCityBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(city: CityInlinedDomainModel) {
        Timber.d("bind(), cityId=${city.id}")
        binding.item = CityInlinedDomainModel(
            city.id,
            city.name,
            city.dt,
            city.dayName,
            city.dayNumber,
            city.temp,
            city.temp_min,
            city.temp_max,
            city.icon,
            city.description
        )

        setWeatherIcon(city.icon)
        chooseCityCardColors(city.temp?.toInt() ?: 0)
        binding.executePendingBindings()
    }

    private fun setWeatherIcon (weatherIcon: String?) {
        Glide.with(context).load(BASE_WEATHER_ICON_API_URL + weatherIcon + WEATHER_ICON_TYPE)
            .into(binding.iCityIvWeatherIcon)
    }

    private fun chooseCityCardColors(temperature: Int) {
        when {
            temperature <= LOWEST_TEMPERATURE_BOUND -> {
                setCityCardColors(Color.YELLOW, Color.GREEN, Color.RED)
            }
            temperature in (MID_TEMPERATURE_BOUND_BOTTOM + 1) until MID_TEMPERATURE_BOUND_TOP -> {
                setCityCardColors(Color.RED, Color.YELLOW, Color.GREEN)
            }
            else -> {
                setCityCardColors(Color.GREEN, Color.RED, Color.YELLOW)
            }
        }
    }

    private fun setCityCardColors(nameColor: Int, temperatureColor: Int, dateColor: Int) {
        binding.iCityTvCityName.setTextColor(nameColor)
        binding.iCityTvTemperature.setTextColor(temperatureColor)
        binding.iCityTvTemperatureSymbol.setTextColor(temperatureColor)
        binding.iCityTvDayName.setTextColor(dateColor)
        binding.iCityTvDayNumber.setTextColor(dateColor)
    }

}