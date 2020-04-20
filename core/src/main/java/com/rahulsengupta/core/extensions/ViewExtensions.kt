package com.rahulsengupta.core.extensions

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rahulsengupta.core.R
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
    ImageViewCompat.setImageTintList(
        this,
        ColorStateList.valueOf(ContextCompat.getColor(context, colorRes))
    )
}

fun ImageView.loadImage(url: String?, block: (GradientDrawable) -> Unit = {}) {
    Glide
        .with(this)
        .asBitmap()
        .load(url)
        .listener(object : RequestListener<Bitmap> {
            override fun onResourceReady(
                resource: Bitmap?,
                model: Any?,
                target: Target<Bitmap>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                setImageBitmap(resource)
                val palette = resource?.createPalette()
                val darkGray = ContextCompat.getColor(context, R.color.darkGray)
                val color = ContextCompat.getColor(context, R.color.translucentRed)
                val gradientDrawable = GradientDrawable(
                    GradientDrawable.Orientation.BOTTOM_TOP,
                    intArrayOf(
                        darkGray,
                        palette?.getDarkMutedColor(color) ?: palette?.getLightVibrantColor(color) ?: color
                    )
                )
                block(gradientDrawable)
                return true
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Bitmap>?,
                isFirstResource: Boolean
            ) = false
        })
        .into(this)
}

fun Bitmap.createPalette(): Palette = Palette.from(this).generate()