package com.example.summacharassignment.data

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "articles")
data class NewsData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val tagId: Int,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: Timestamp,
    val content: String?,
    val sourceId: String?,
    val sourceName: String
)