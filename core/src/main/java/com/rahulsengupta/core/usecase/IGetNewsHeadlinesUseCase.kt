package com.rahulsengupta.core.usecase

import com.rahulsengupta.persistence.dao.HeadlinesDao
import com.rahulsengupta.persistence.enitity.ArticleEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

interface IGetNewsHeadlinesUseCase {
    val flow: Flow<List<ArticleEntity>?>
}

class GetNewsHeadlinesUseCase @Inject constructor(
    private val headlinesDao: HeadlinesDao
) : IGetNewsHeadlinesUseCase {

    @ExperimentalCoroutinesApi
    override val flow: Flow<List<ArticleEntity>?>
        get() = headlinesDao.getHeadlines().distinctUntilChanged()

}