package com.rahulsengupta.core.customview

import android.content.Context
import android.util.AttributeSet
import com.robinhood.spark.SparkView

class CustomSparkView : SparkView {

    lateinit var listener: ScrubListener

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun onScrubEnded() {
        super.onScrubEnded()
        listener.onScrubEndedListener()
    }
}

interface ScrubListener {
    fun onScrubEndedListener()
}