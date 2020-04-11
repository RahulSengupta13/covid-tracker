package com.rahulsengupta.core.usecase

import com.rahulsengupta.persistence.dao.GlobalTimelineDao
import com.rahulsengupta.persistence.enitity.GlobalTimelineEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

interface IGetGlobalTimelineUseCase {
    val flow: Flow<GlobalTimelineEntity>
}

class GetGlobalTimelineUseCase @Inject constructor(
    private val globalTimelineDao: GlobalTimelineDao
): IGetGlobalTimelineUseCase {

    override val flow: Flow<GlobalTimelineEntity>
        get() = globalTimelineDao.getGlobalTimeline().distinctUntilChanged()

}