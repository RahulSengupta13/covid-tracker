package com.rahulsengupta.core.bindingadapters

import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButtonToggleGroup

@BindingAdapter("app:setChecked")
fun setChecked(group: MaterialButtonToggleGroup, @IdRes btnId: Int?) {
    btnId?.let { group.check(it) }
}