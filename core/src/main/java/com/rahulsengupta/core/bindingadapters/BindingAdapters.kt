package com.rahulsengupta.core.bindingadapters

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButtonToggleGroup
import com.rahulsengupta.core.extensions.setTint

@BindingAdapter("app:setChecked")
fun setChecked(group: MaterialButtonToggleGroup, @IdRes btnId: Int?) {
    btnId?.let { group.check(it) }
}

@BindingAdapter("app:setTint")
fun setTint(imageView: ImageView, @ColorRes color: Int?) {
    color?.let {
        imageView.setTint(it)
    }
}