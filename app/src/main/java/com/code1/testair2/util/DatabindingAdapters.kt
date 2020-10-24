package com.code1.testair2.util

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("data")
fun <T> setRecyclerViewData(recyclerView: RecyclerView, data: T?) {
    if (recyclerView.adapter is BindableRecyclerAdapter<*>) {
        (recyclerView.adapter as BindableRecyclerAdapter<T>).setData(data)
    }
}

@BindingAdapter("visible")
fun setVisible(view: View, isVisible: Boolean) {
    if (isVisible && view.visibility == View.GONE) view.visibility = View.VISIBLE
    if (!isVisible && view.visibility == View.VISIBLE) view.visibility = View.GONE
}