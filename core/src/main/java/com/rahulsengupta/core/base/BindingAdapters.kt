package com.rahulsengupta.core.base

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("app:scrollToPosition")
fun scrollToPosition(recyclerView: RecyclerView, position: Int?) {
    position?.let {
        recyclerView.smoothScrollToPosition(it)
    }
}