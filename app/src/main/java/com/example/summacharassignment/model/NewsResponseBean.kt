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
            get() {
                return field
            }
            set(value) {
                field = value
            }


    @SerializedName("totalResults")
    @Expose
    var totalResults: Int = 0
        get() {
            return field
        }
        set(value) {
            field = value
        }

    @SerializedName("articles")
    @Expose
    var articles: List<Articles> = mutableListOf()
        get() {
            return field
        }
        set(value) {
            field = value
        }


    class Articles: Serializable{
        @SerializedName("source")
        @Expose
        var source: Source = Source()
            get() {
                return field
            }
            set(value) {
                field = value
            }

        @SerializedName("author")
        @Expose
        var author: String = ""
            get() {
                return field
            }
            set(value) {
                field = value
            }

        @SerializedName("title")
        @Expose
        var title: String = ""
            get() {
                return field
            }
            set(value) {
                field = value
            }

        @SerializedName("description")
        @Expose
        var description: String = ""
            get() {
                return field
            }
            set(value) {
                field = value
            }

        @SerializedName("url")
        @Expose
        var url: String = ""
            get() {
                return field
            }
            set(value) {
                field = value
            }

        @SerializedName("urlToImage")
        @Expose
        var urlToImage: String = ""
            get() {
                return field
            }
            set(value) {
                field = value
            }

        @SerializedName("publishedAt")
        @Expose
        var publishedAt: Timestamp? = null
            get() {
                return field
            }
            set(value) {
                field = value
            }

        @SerializedName("content")
        @Expose
        var content: String = ""
            get() {
                return field
            }
            set(value) {
                field = value
            }

        class Source: Serializable{
            @SerializedName("id")
            @Expose
            var id: String = ""
                get() {
                    return field
                }
                set(value) {
                    field = value
                }

            @SerializedName("name")
            @Expose
            var name: String = ""
                get() {
                    return field
                }
                set(value) {
                    field = value
                }
        }
    }

}