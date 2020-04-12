package com.rahulsengupta.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.rahulsengupta.persistence.enitity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HeadlinesDao : BaseDao<ArticleEntity> {


    @Query("SELECT * FROM Article")
    fun getHeadlines(): Flow<List<ArticleEntity>?>


    @Query("SELECT COUNT(*) FROM Article")
    fun getHeadlinesCount(): Int

}