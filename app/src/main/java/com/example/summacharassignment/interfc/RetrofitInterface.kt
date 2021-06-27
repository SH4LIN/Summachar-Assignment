package com.example.summacharassignment.interfc

import com.example.summacharassignment.model.NewsResponseBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitInterface {

    @GET("/v2/top-headlines")
    fun getTopHeadlines(@Query("country") country:String): Call<NewsResponseBean>

    @GET("/v2/top-headlines")
    fun getNewsCategoryWise(@Query("category") category:String): Call<NewsResponseBean>

}