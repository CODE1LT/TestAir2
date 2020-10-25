package com.code1.testair2.common.errorhandler

import android.view.View
import androidx.annotation.StringRes

interface ErrorHandler {
    fun showError(container: View, throwable: Throwable)
    fun showError(container: View, message: String)
    fun showError(container: View, @StringRes messageRes: Int)
}