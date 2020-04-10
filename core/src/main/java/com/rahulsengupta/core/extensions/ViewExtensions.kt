package com.rahulsengupta.core.extensions

import android.view.animation.OvershootInterpolator
import com.robinhood.ticker.TickerUtils
import com.robinhood.ticker.TickerView

fun TickerView.setDefaults() {
    setCharacterLists(TickerUtils.provideNumberList())
    animationDuration = 300
    animationInterpolator = OvershootInterpolator()
    setPreferredScrollingDirection(TickerView.ScrollingDirection.ANY)
}