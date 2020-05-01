package com.rahulsengupta.persistence.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "Article")
data class ArticleEntity(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: Long,
    val source: Source?,
    @PrimaryKey
    val title: String,
    val url: String,
    val urlToImage: String?
) {
    @Serializable
    data class Source(
        val id: String?,
        val name: String?
    )
}
