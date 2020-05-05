package com.rahulsengupta.core.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

fun Context.getScreenWidth(): Int {

    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val point = Point()
    windowManager.defaultDisplay.getSize(point)
    return point.x
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}