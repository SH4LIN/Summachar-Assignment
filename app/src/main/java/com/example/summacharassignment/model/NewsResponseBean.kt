package com.example.summacharassignment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.net.URL
import java.sql.Timestamp

class NewsResponseBean: Serializable {

    @SerializedName("status")
    @Expose
    var status: String = ""


    @SerializedName("totalResults")
    @Expose
    var totalResults: Int = 0

    @SerializedName("articles")
    @Expose
    var articles: MutableList<Articles> = mutableListOf()


    class Articles: Serializable{
        @SerializedName("source")
        @Expose
        var source: Source = Source()

        @SerializedName("author")
        @Expose
        var author: String = ""

        @SerializedName("title")
        @Expose
        var title: String = ""

        @SerializedName("description")
        @Expose
        var description: String = ""

        @SerializedName("url")
        @Expose
        var url: String = ""

        @SerializedName("urlToImage")
        @Expose
        var urlToImage: String = ""

        @SerializedName("publishedAt")
        @Expose
        var publishedAt: Timestamp? = null

        @SerializedName("content")
        @Expose
        var content: String = ""

        class Source: Serializable{
            @SerializedName("id")
            @Expose
            var id: String = ""

            @SerializedName("name")
            @Expose
            var name: String = ""
        }
    }

}