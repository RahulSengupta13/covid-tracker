package com.rahulsengupta.persistence.usecase

import com.rahulsengupta.persistence.dao.GlobalTimelineDao
import com.rahulsengupta.persistence.enitity.GlobalTimelineEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

interface IGetGlobalTimelineUseCase {
    val flow: Flow<GlobalTimelineEntity?>
}

class GetGlobalTimelineUseCase @Inject constructor(
    private val globalTimelineDao: GlobalTimelineDao
): IGetGlobalTimelineUseCase {

    @ExperimentalCoroutinesApi
    override val flow: Flow<GlobalTimelineEntity?>
        get() = globalTimelineDao.getGlobalTimeline().distinctUntilChanged()

}