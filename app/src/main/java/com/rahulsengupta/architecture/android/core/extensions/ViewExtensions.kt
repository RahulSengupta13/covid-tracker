package com.rahulsengupta.architecture.android.core.extensions

import android.graphics.Bitmap
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rahulsengupta.architecture.R

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

                val palette = resource?.createPalette() ?: return true

                val colorSecondary = ContextCompat.getColor(context, R.color.color_on_secondary)
                val gray = ContextCompat.getColor(context, R.color.gray)

                var bottomColor = palette.getDarkMutedColor(gray)

                if(bottomColor == gray) {
                    bottomColor = palette.getMutedColor(gray)
                }

                val gradientDrawable = GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    intArrayOf(
                        colorSecondary,
                        bottomColor
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