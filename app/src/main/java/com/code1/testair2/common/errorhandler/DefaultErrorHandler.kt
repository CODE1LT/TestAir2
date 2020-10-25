package com.code1.testair2.common.errorhandler

import android.view.View
import com.code1.testair2.R
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject
import kotlin.reflect.KClass

open class DefaultErrorHandler @Inject constructor() : ErrorHandler {

    protected val specificThowableMessageMap: MutableMap<KClass<*>, Int> =
        DefaultErrorMessages.messages

    override fun showError(container: View, message: String) {
        displayErrorMessage(container, message)
    }

    override fun showError(container: View, messageRes: Int) {
        displayErrorMessage(container, container.resources.getString(messageRes))
    }

    override fun showError(container: View, throwable: Throwable) {
        val errorMessage = container.resources.getString(
            specificThowableMessageMap[throwable::class] ?: R.string.error_generic
        )
        displayErrorMessage(container, errorMessage)
    }

    protected open fun displayErrorMessage(container: View, message: String) {
        Snackbar.make(container, message, Snackbar.LENGTH_LONG).show()
    }
}