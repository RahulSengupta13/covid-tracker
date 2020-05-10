package com.rahulsengupta.architecture.android.flows.search

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.doOnTextChanged
import com.rahulsengupta.architecture.R
import com.rahulsengupta.core.extensions.hideKeyboard
import kotlinx.android.synthetic.main.search_toolbar.view.*

class SearchToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.search_toolbar, this, true)
        setup()
    }

    private fun setup() {
        search_title.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty()) {
                search_close_icon.visibility = View.VISIBLE
            } else {
                search_close_icon.visibility = View.GONE
            }
        }

        search_close_icon.setOnClickListener {
            search_title.setText("")
            search_title.clearFocus()
            context.hideKeyboard(search_close_icon)
        }

        val suggestions: List<String> = listOf("united states", "united kingdom", "united india")
        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, suggestions)
        search_title.setAdapter(adapter)
        search_title.dropDownVerticalOffset =
            resources.getDimensionPixelOffset(R.dimen.standard_half_margin)
    }
}