package com.example.summacharassignment.interfc

import com.example.summacharassignment.model.NewsResponseBean
import retrofit2.Response

interface ApiResponseHandler {
    fun onApiSuccess(response: Response<NewsResponseBean>)

    fun onApiFailure(t: Throwable)
}