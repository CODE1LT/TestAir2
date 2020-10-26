package com.code1.testair2.feature.citieslist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code1.testair2.common.events.Event
import com.code1.testair2.feature.citieslist.domain.model.CityInlinedDomainModel
import com.code1.testair2.feature.citieslist.domain.usecase.FetchCityUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.code1.testair2.common.Result
import com.code1.testair2.feature.citieslist.domain.usecase.GetCitiesListUseCase

class CitiesListFragmentViewModel @Inject constructor(
    val cityName: String?,
    private val fetchCityUseCase: FetchCityUseCase,
    private val getCitiesListUseCase: GetCitiesListUseCase
) : ViewModel() {

    val loading = MutableLiveData<Boolean>().apply { value = false }
    val onError = MutableLiveData<Event<Exception>>()
    val cityList = MutableLiveData<List<CityInlinedDomainModel>>()

    fun getCity() {
        viewModelScope.launch {
            loading.value = true
            fetchCityUseCase.invoke(cityName ?: "").collect { result ->
                when (result) {
                    is Result.Success -> {
                        loading.value = false
                        cityList.value = result.data
                    }
                    is Result.Error -> {
                        loading.value = false
                        onError.value = Event(result.exception)
                    }
                }
            }
        }
    }

    fun getSearchHistory() {
        viewModelScope.launch {
            loading.value = true
            getCitiesListUseCase.invoke().collect { result ->
                when (result) {
                    is Result.Success -> {
                        loading.value = false
                        cityList.value = result.data
                    }
                    is Result.Error -> {
                        loading.value = false
                        onError.value = Event(result.exception)
                    }
                }
            }
        }
    }

}