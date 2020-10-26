package com.code1.testair2.feature.citysearch.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.code1.testair2.common.events.Event
import javax.inject.Inject

class CitySearchFragmentViewModel @Inject constructor() : ViewModel() {

    val searchText = MutableLiveData<String>().apply { value = "" }
    val cityNameSubmitedEvent = MutableLiveData<Event<String?>>()
    val retrieveSearchHistoryEvent = MutableLiveData<Event<Unit>>()

    fun onSearchButtonClicked() {
        cityNameSubmitedEvent.value = Event(searchText.value)
    }

    fun onHistoryButtonClicked() {
        retrieveSearchHistoryEvent.value = Event(Unit)
    }
}