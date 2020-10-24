package com.code1.testair2.feature.launcher.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import javax.inject.Inject

class LauncherViewModel @Inject constructor() : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }

    fun showLoading() {
        Timber.d("showLoading()")
        isLoading.postValue(true)
    }

    fun hideLoading() {
        Timber.d("hideLoading()")
        isLoading.postValue(false)
    }
}