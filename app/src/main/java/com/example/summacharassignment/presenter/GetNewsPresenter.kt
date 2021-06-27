package com.example.summacharassignment.presenter

import android.util.Log
import com.example.summacharassignment.BuildConfig
import com.example.summacharassignment.R
import com.example.summacharassignment.interfc.ApiResponseHandler
import com.example.summacharassignment.model.NewsResponseBean
import com.example.summacharassignment.utils.Utilities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetNewsPresenter {
    fun getTopHeadlines(apiResponseHandler: ApiResponseHandler){

        val call = Utilities.create().initWebServiceCall(BuildConfig.BASE_URL).getTopHeadlines("in")
        call.enqueue(object : Callback<NewsResponseBean>{
            override fun onResponse(
                call: Call<NewsResponseBean>,
                response: Response<NewsResponseBean>
            ) {
                if (response.code() == 200) {
                    apiResponseHandler.onApiSuccess(response)
                }else{
                    Log.e("Api Error",response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<NewsResponseBean>, t: Throwable) {
                apiResponseHandler.onApiFailure(t)
            }

        })
    }

    fun getSportsNews(apiResponseHandler: ApiResponseHandler){

        val call = Utilities.create().initWebServiceCall(BuildConfig.BASE_URL).getNewsCategoryWise("sports")
        call.enqueue(object : Callback<NewsResponseBean>{
            override fun onResponse(
                call: Call<NewsResponseBean>,
                response: Response<NewsResponseBean>
            ) {
                if (response.code() == 200) {
                    apiResponseHandler.onApiSuccess(response)
                }else{
                    Log.e("Api Error",response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<NewsResponseBean>, t: Throwable) {
                apiResponseHandler.onApiFailure(t)
            }

        })
    }

    fun getBusinessNews(apiResponseHandler: ApiResponseHandler){

        val call = Utilities.create().initWebServiceCall(BuildConfig.BASE_URL).getNewsCategoryWise("business")
        call.enqueue(object : Callback<NewsResponseBean>{
            override fun onResponse(
                call: Call<NewsResponseBean>,
                response: Response<NewsResponseBean>
            ) {
                if (response.code() == 200) {
                    apiResponseHandler.onApiSuccess(response)
                }else{
                    Log.e("Api Error",response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<NewsResponseBean>, t: Throwable) {
                apiResponseHandler.onApiFailure(t)
            }

        })
    }

    fun getScienceNews(apiResponseHandler: ApiResponseHandler){

        val call = Utilities.create().initWebServiceCall(BuildConfig.BASE_URL).getNewsCategoryWise("science")
        call.enqueue(object : Callback<NewsResponseBean>{
            override fun onResponse(
                call: Call<NewsResponseBean>,
                response: Response<NewsResponseBean>
            ) {
                if (response.code() == 200) {
                    apiResponseHandler.onApiSuccess(response)
                }else{
                    Log.e("Api Error",response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<NewsResponseBean>, t: Throwable) {
                apiResponseHandler.onApiFailure(t)
            }

        })
    }

    fun getHealthNews(apiResponseHandler: ApiResponseHandler){

        val call = Utilities.create().initWebServiceCall(BuildConfig.BASE_URL).getNewsCategoryWise("health")
        call.enqueue(object : Callback<NewsResponseBean>{
            override fun onResponse(
                call: Call<NewsResponseBean>,
                response: Response<NewsResponseBean>
            ) {
                if (response.code() == 200) {
                    apiResponseHandler.onApiSuccess(response)
                }else{
                    Log.e("Api Error",response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<NewsResponseBean>, t: Throwable) {
                apiResponseHandler.onApiFailure(t)
            }

        })
    }


}