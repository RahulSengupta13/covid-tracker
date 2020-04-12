package com.rahulsengupta.architecture.android.flows.dashboard.model

sealed class NewsItem(
    open val viewType: Int
) {

    data class Headline(
        val title: String,
        val publishedAt: String,
        val imageUrl: String,
        val url: String,
        override val viewType: Int = NewsItemViewType.HEADLINE.viewType
    ) : NewsItem(viewType)

    data class More(
        override val viewType: Int = NewsItemViewType.MORE.viewType
    ) : NewsItem(viewType)
}

enum class NewsItemViewType(val viewType: Int) {
    HEADLINE(0),
    MORE(1)
}