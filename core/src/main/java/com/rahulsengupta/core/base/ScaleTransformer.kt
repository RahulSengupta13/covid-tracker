package com.rahulsengupta.core.base

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class ScaleTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.apply {
            val delta = 1 - abs(position)
            val scale = 0.95f + delta * 0.05f
            scaleY = scale
            scaleX = scale
        }
    }
}