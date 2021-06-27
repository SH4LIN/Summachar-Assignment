package com.example.summacharassignment.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNews(newsData: NewsData)

    @Query("SELECT * FROM articles ORDER BY id ASC")
    fun readAllNews(): LiveData<List<NewsData>>

    @Query("SELECT * FROM articles WHERE tagId = :pageId ORDER BY id ASC")
    fun filterNewsByTagId(pageId:Int): List<NewsData>

    @Query("DELETE FROM articles WHERE tagId = :pageId")
    fun deleteAllDataOfPageId(pageId: Int): Int

}