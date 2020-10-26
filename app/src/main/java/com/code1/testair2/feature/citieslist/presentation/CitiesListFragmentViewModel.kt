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

class CitiesListFragmentViewModel @Inject constructor(
    val cityName: String?,
    private val fetchCityUseCase: FetchCityUseCase
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
                        //loading.value = false
                        cityList.value = result.data
                    }
                    is Result.Error -> {
                        //loading.value = false
                        onError.value = Event(result.exception)
                    }
                }
            }
        }
    }

//    val viewLiveData = ViewLiveData(CitiesListFragmentViewLiveData())
//    private val viewLiveDataValue = viewLiveData.value
//    val notificationEvent = SingleLiveEvent<Notification>()
//
//    fun getCity(cityAndCountryName: String) {
//        viewLiveData.addSingleResourceSource(
//            fetchCityInteractor.getSingle(cityAndCountryName),
//            { manageFetchCityDataState(it) },
//            { manageFetchCityLoadingState() },
//            { error, _ -> manageFetchCityErrorState(error) }
//        )
//        viewLiveData.notifyLiveDataObservers()
//    }

//    fun getSearchHistory() {
//        viewLiveData.addSingleResourceSource(
//            getCitiesListInteractor.getSingle(),
//            { manageFetchCityDataState(it) },
//            { manageFetchCityLoadingState() },
//            { error, _ -> manageFetchCityErrorState(error) }
//        )
//        viewLiveData.notifyLiveDataObservers()
//    }
//
//    private fun manageFetchCityLoadingState() {
//        viewLiveDataValue.dataIsLoading = true
//        handleLoading()
//    }
//
//    private fun manageFetchCityDataState(citiesList: List<City>) {
//        viewLiveDataValue.dataIsLoading = false
//        handleLoading()
//        citiesList
//            .let { viewLiveDataValue.citiesList = it }
//        viewLiveData.notifyLiveDataObservers()
//    }

//    private fun manageFetchCityErrorState(error: Throwable) {
//        viewLiveDataValue.dataIsLoading = false
//        handleLoading()
//        showError(error.message.toString())
//    }
//
//
//    private fun showError(errorMessage: String) {
//        notificationEvent.postValue(
//            Notification(
//                false,
//                R.string.f_cities_list_tst_completed_successfully_text,
//                getErrorMessage(errorMessage)
//            )
//        )
//    }
//
//    private fun getErrorMessage(message: String): String {
//        return if (message.contains("404", ignoreCase = true)) {
//            context.resources.getString(R.string.error_not_found)
//        } else {
//            message
//        }
//    }
//
//    private fun handleLoading() {
//        if (viewLiveDataValue.dataIsLoading) {
//            fragmentsListener.showLoading()
//        } else {
//            fragmentsListener.hideLoading()
//        }
//    }

}