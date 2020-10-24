package com.code1.testair2.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.util.TypedValue
import android.view.View

import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import kotlin.math.roundToInt

class SoftInputHelper {
    fun hideKeyboard(view: View, activity: FragmentActivity?) {
        activity?.let { getInputMethodManager(it).hideSoftInputFromWindow(view.windowToken, 0) }
    }

    fun showKeyboard(view: View, activity: FragmentActivity?) {
        activity?.let {
            getInputMethodManager(it).showSoftInput(
                view,
                InputMethodManager.RESULT_SHOWN
            )
        }
        activity?.let {
            getInputMethodManager(it).toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        }
    }

    private fun getInputMethodManager(activity: FragmentActivity): InputMethodManager {
        return activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    fun isKeyboardOpen(rootView: View, resources: Resources): Boolean {
        val visibleBounds = Rect()
        rootView.getWindowVisibleDisplayFrame(visibleBounds)
        val heightDiff = rootView.height - visibleBounds.height()
        val marginOfError = this.convertDpToPx(50F, resources).roundToInt()
        return heightDiff > marginOfError
    }

    private fun convertDpToPx(dp: Float, resources: Resources) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        resources.displayMetrics
    )

}