package com.example.summacharassignment.data

import androidx.lifecycle.LiveData
import androidx.room.*
import java.sql.Timestamp

@Dao
interface NewsDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNews(newsData: NewsData)

    @Query("SELECT * FROM articles ORDER BY id ASC")
    fun readAllNews(): LiveData<List<NewsData>>

    @Query("SELECT * FROM articles WHERE tagId = :pageId ORDER BY publishedAt DESC")
    fun filterNewsByTagId(pageId:Int): List<NewsData>

    @Query("DELETE FROM articles WHERE tagId = :pageId")
    fun deleteAllDataOfPageId(pageId: Int): Int

    @Query("SELECT COUNT(*) FROM articles WHERE tagId = :pageId")
    fun checkDataExistForTag(pageId: Int): Int

    @Query("SELECT EXISTS(SELECT * FROM articles WHERE tagId = :pageId AND title = :title  AND url = :url AND publishedAt = :publishedAt )")
    fun isRowExist(pageId: Int,title:String,url:String,publishedAt:Timestamp) : Boolean

}