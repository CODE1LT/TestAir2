package lt.code1.testair.features.citieslist.data

data class City(
    val id: Long?,
    val name: String?,
    val dt: Long?,
    val dayName: String?,
    val dayNumber: String?,
    val temp: String?,
    val temp_min: Float?,
    val temp_max: Float?,
    val icon: String?,
    val description: String?
)