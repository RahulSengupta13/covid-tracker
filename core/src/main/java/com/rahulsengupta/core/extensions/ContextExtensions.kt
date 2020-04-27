package com.rahulsengupta.core.extensions

import android.content.Context
import android.graphics.Point
import android.view.WindowManager

fun Context.getScreenWidth(): Int {

    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val point = Point()
    windowManager.defaultDisplay.getSize(point)
    return point.x
}