package com.example.summacharassignment.data

import java.sql.Timestamp

data class NewsData(
    val tag: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: Timestamp,
    val content: String,
    val sourceId: String,
    val sourceName: String
)