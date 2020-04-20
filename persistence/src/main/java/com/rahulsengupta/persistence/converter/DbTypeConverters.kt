package com.rahulsengupta.persistence.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rahulsengupta.persistence.enitity.ArticleEntity
import com.rahulsengupta.persistence.enitity.GlobalCountryEntity
import com.rahulsengupta.persistence.enitity.GlobalTimelineEntity.GlobalTimelineValue

class DbTypeConverters {

    @TypeConverter
    fun fromString(value: String): Map<String, Int> {
        val mapType = object : TypeToken<Map<String, Int>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromStringMap(map: Map<String, Int>): String {
        return Gson().toJson(map)
    }

    @TypeConverter
    fun fromGlobalTimeline(data: List<GlobalTimelineValue>): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toGlobalTimeline(value: String): List<GlobalTimelineValue> {
        val mapType = object : TypeToken<List<GlobalTimelineValue>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromArticleSource(data: ArticleEntity.Source): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toArticleSource(value: String): ArticleEntity.Source {
        val mapType = object : TypeToken<ArticleEntity.Source>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromGlobalCountryInfo(data: GlobalCountryEntity.CountryInfo): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toGlobalCountryInfo(value: String): GlobalCountryEntity.CountryInfo {
        val mapType = object : TypeToken<GlobalCountryEntity.CountryInfo>() {}.type
        return Gson().fromJson(value, mapType)
    }
}