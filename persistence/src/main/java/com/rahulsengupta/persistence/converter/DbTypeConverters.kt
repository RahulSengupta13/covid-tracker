package com.rahulsengupta.persistence.converter

import androidx.room.TypeConverter
import com.rahulsengupta.persistence.CovidTrackerApplicationDatabase.Companion.json
import com.rahulsengupta.persistence.enitity.ArticleEntity
import com.rahulsengupta.persistence.enitity.GlobalCountryEntity
import com.rahulsengupta.persistence.enitity.GlobalTimelineEntity.GlobalTimelineValue
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.serializer
import kotlinx.serialization.stringify

class DbTypeConverters {

    @ImplicitReflectionSerializer
    @TypeConverter
    fun fromString(value: String): Map<String, Int> = json.parse(serializer(), value)

    @ImplicitReflectionSerializer
    @TypeConverter
    fun fromStringMap(map: Map<String, Int>) = json.stringify(map)

    @ImplicitReflectionSerializer
    @TypeConverter
    fun fromGlobalTimeline(data: List<GlobalTimelineValue>) = json.stringify(data)

    @ImplicitReflectionSerializer
    @TypeConverter
    fun toGlobalTimeline(value: String): List<GlobalTimelineValue> = json.parse(serializer(), value)

    @ImplicitReflectionSerializer
    @TypeConverter
    fun fromArticleSourceEntity(data: ArticleEntity.Source) = json.stringify(data)

    @ImplicitReflectionSerializer
    @TypeConverter
    fun toArticleSourceEntity(value: String): ArticleEntity.Source = json.parse(serializer(), value)

    @ImplicitReflectionSerializer
    @TypeConverter
    fun fromGlobalCountryInfo(data: GlobalCountryEntity.CountryInfo) = json.stringify(data)

    @ImplicitReflectionSerializer
    @TypeConverter
    fun toGlobalCountryInfo(value: String): GlobalCountryEntity.CountryInfo =
        json.parse(serializer(), value)
}