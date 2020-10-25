package com.code1.testair2.feature.citieslist.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CitiesListFragmentViewModel @Inject constructor(
    private val context: Context,
    private val fragmentsListener: FragmentsListener,
    private val fetchCityInteractor: RetrieveSingleInteractorWithParams<String, Resource<@JvmSuppressWildcards List<City>>>,
    private val getCitiesListInteractor: RetrieveSingleInteractor<Resource<@JvmSuppressWildcards List<City>>>
) : ViewModel() {

    val viewLiveData = ViewLiveData(CitiesListFragmentViewLiveData())
    private val viewLiveDataValue = viewLiveData.value
    val notificationEvent = SingleLiveEvent<Notification>()

    fun getCity(cityAndCountryName: String) {
        viewLiveData.addSingleResourceSource(
            fetchCityInteractor.getSingle(cityAndCountryName),
            { manageFetchCityDataState(it) },
            { manageFetchCityLoadingState() },
            { error, _ -> manageFetchCityErrorState(error) }
        )
        viewLiveData.notifyLiveDataObservers()
    }

    fun getSearchHistory() {
        viewLiveData.addSingleResourceSource(
            getCitiesListInteractor.getSingle(),
            { manageFetchCityDataState(it) },
            { manageFetchCityLoadingState() },
            { error, _ -> manageFetchCityErrorState(error) }
        )
        viewLiveData.notifyLiveDataObservers()
    }

    private fun manageFetchCityLoadingState() {
        viewLiveDataValue.dataIsLoading = true
        handleLoading()
    }

    private fun manageFetchCityDataState(citiesList: List<City>) {
        viewLiveDataValue.dataIsLoading = false
        handleLoading()
        citiesList
            .let { viewLiveDataValue.citiesList = it }
        viewLiveData.notifyLiveDataObservers()
    }

    private fun manageFetchCityErrorState(error: Throwable) {
        viewLiveDataValue.dataIsLoading = false
        handleLoading()
        showError(error.message.toString())
    }


    private fun showError(errorMessage: String) {
        notificationEvent.postValue(
            Notification(
                false,
                R.string.f_cities_list_tst_completed_successfully_text,
                getErrorMessage(errorMessage)
            )
        )
    }

    private fun getErrorMessage(message: String): String {
        return if (message.contains("404", ignoreCase = true)) {
            context.resources.getString(R.string.error_not_found)
        } else {
            message
        }
    }

    private fun handleLoading() {
        if (viewLiveDataValue.dataIsLoading) {
            fragmentsListener.showLoading()
        } else {
            fragmentsListener.hideLoading()
        }
    }

}