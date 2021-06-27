package com.example.summacharassignment.data

import androidx.lifecycle.LiveData

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
}