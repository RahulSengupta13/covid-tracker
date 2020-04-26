package com.rahulsengupta.core.usecase

import com.rahulsengupta.core.extensions.getLongFromTimeStamp
import com.rahulsengupta.network.usecase.IFetchHeadlinesUseCase
import com.rahulsengupta.persistence.dao.HeadlinesDao
import com.rahulsengupta.persistence.enitity.ArticleEntity
import javax.inject.Inject

interface ILoadHeadlinesUseCase {
    suspend fun loadHeadlines(
        query: String,
        sortBy: String,
        language: String,
        pageSize: Int,
        page: Int,
        country: String
    )

    suspend fun hasHeadlines(): Boolean
}

class LoadHeadlinesUseCase @Inject constructor(
    private val useCase: IFetchHeadlinesUseCase,
    private val dao: HeadlinesDao
) : ILoadHeadlinesUseCase {

    override suspend fun loadHeadlines(
        query: String,
        sortBy: String,
        language: String,
        pageSize: Int,
        page: Int,
        country: String
    ) {
        val headlines = useCase.fetchHeadlines(
            "COVID",
            "publishedAt",
            "en",
            100,
            1,
            ""
        ) ?: return
        val articles = headlines.articles.map {
            ArticleEntity(
                it.author,
                it.content,
                it.description,
                it.publishedAt.getLongFromTimeStamp(),
                ArticleEntity.Source(
                    it.source?.id,
                    it.source?.name
                ),
                it.title,
                it.url,
                it.urlToImage
            )
        }
        dao.insertAllOrReplace(articles)
    }

    override suspend fun hasHeadlines() = dao.getHeadlinesCount() > 0
}