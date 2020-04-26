package com.rahulsengupta.core.usecase

import com.rahulsengupta.core.extensions.getFormattedDateFromUTCTimestamp
import com.rahulsengupta.network.usecase.IFetchGlobalTimelineUseCase
import com.rahulsengupta.persistence.dao.GlobalTimelineDao
import com.rahulsengupta.persistence.enitity.GlobalTimelineEntity
import javax.inject.Inject

interface ILoadGlobalTimelineUseCase {
    suspend fun loadGlobalTimeline()
    suspend fun hasGlobalTimeline(): Boolean
}

class LoadGlobalTimelineUseCase @Inject constructor(
    private val useCase: IFetchGlobalTimelineUseCase,
    private val dao: GlobalTimelineDao
) : ILoadGlobalTimelineUseCase {

    override suspend fun loadGlobalTimeline() {
        val globalTimeline = useCase.fetchGlobalTimeline()?.data ?: return
        val globalTimelineEntity = GlobalTimelineEntity(
            list = globalTimeline.map {
                GlobalTimelineEntity.GlobalTimelineValue(
                    it.newConfirmed,
                    it.newRecovered,
                    it.newDeaths,
                    it.updatedAt.getFormattedDateFromUTCTimestamp()
                )
            }.asReversed()
        )
        dao.insertOrReplace(globalTimelineEntity)
    }

    override suspend fun hasGlobalTimeline() = dao.getGlobalTimelineCount() > 0
}