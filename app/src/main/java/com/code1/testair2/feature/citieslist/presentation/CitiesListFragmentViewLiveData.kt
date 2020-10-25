package com.code1.testair2.feature.citieslist.presentation

import lt.code1.testair.archextensions.ViewData

data class CitiesListFragmentViewLiveData(
    var citiesList: List<Any>? = arrayListOf(),
    var dataIsLoading: Boolean = false
) : ViewData