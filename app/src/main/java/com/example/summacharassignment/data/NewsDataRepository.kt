package com.example.summacharassignment.data

import androidx.lifecycle.LiveData
import java.sql.Timestamp

class NewsDataRepository(private val newsDataDao: NewsDataDao) {

    val readAllNews: LiveData<List<NewsData>> = newsDataDao.readAllNews()

    suspend fun adduser(newsData: NewsData){
        newsDataDao.addNews(newsData)
    }

    fun filterNewsByTagId(pageId:Int): List<NewsData>{
        return newsDataDao.filterNewsByTagId(pageId)
    }

    fun deleteAllDataOfPageId(pageId:Int): Int{
        return newsDataDao.deleteAllDataOfPageId(pageId)
    }

    fun isRowExist(pageId: Int,title:String,url: String,publishedAt:Timestamp): Boolean{
        return newsDataDao.isRowExist(pageId,title,url,publishedAt)
    }

    fun checkDataExistForTag(pageId: Int): Int{
        return newsDataDao.checkDataExistForTag(pageId)
    }
}