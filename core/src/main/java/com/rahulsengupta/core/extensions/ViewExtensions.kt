package com.rahulsengupta.core.extensions

import android.content.res.ColorStateList
import android.graphics.Typeface
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.robinhood.ticker.TickerUtils
import com.robinhood.ticker.TickerView

fun TickerView.setDefaults() {
    setCharacterLists(TickerUtils.provideNumberList())
    animationDuration = 300
    animationInterpolator = OvershootInterpolator()
    setPreferredScrollingDirection(TickerView.ScrollingDirection.ANY)
    typeface = Typeface.MONOSPACE
}

fun ImageView.setTint(@ColorRes colorRes: Int) {
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(ContextCompat.getColor(context, colorRes)))
}