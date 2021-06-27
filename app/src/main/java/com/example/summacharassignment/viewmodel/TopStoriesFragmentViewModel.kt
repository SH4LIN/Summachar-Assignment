package com.example.summacharassignment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summacharassignment.BuildConfig
import com.example.summacharassignment.model.NewsResponseBean
import com.example.summacharassignment.utils.TabItemsModel
import com.example.summacharassignment.utils.Utilities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopStoriesFragmentViewModel: ViewModel() {

    val data = MutableLiveData<NewsResponseBean>()


    fun getDataObserver(): MutableLiveData<NewsResponseBean> {
        return this.data
    }


    fun addData(response: NewsResponseBean){
        data.value = response
    }


    fun callApi(page:Int){
            when(page){
                TabItemsModel.TOPSTORIES.id -> this.getTopHeadlines()

                TabItemsModel.SPORTS.id -> this.getSportsNews()

                TabItemsModel.BUSINESS.id -> this.getBusinessNews()

                TabItemsModel.SCIENCE.id -> this.getScienceNews()

                TabItemsModel.HEALTH.id -> this.getHealthNews()
            }

    }

    fun getTopHeadlines(){

        val call = Utilities.create().initWebServiceCall(BuildConfig.BASE_URL).getTopHeadlines("in")
        call.enqueue(object : Callback<NewsResponseBean> {
            override fun onResponse(
                call: Call<NewsResponseBean>,
                response: Response<NewsResponseBean>
            ) {
                addData(response.body()!!)
            }

            override fun onFailure(call: Call<NewsResponseBean>, t: Throwable) {

            }

        })
    }

    fun getSportsNews(){

        val call = Utilities.create().initWebServiceCall(BuildConfig.BASE_URL).getNewsCategoryWise("sports")
        call.enqueue(object : Callback<NewsResponseBean> {
            override fun onResponse(
                call: Call<NewsResponseBean>,
                response: Response<NewsResponseBean>
            ) {
                addData(response.body()!!)
            }

            override fun onFailure(call: Call<NewsResponseBean>, t: Throwable) {

            }

        })
    }

    fun getBusinessNews(){

        val call = Utilities.create().initWebServiceCall(BuildConfig.BASE_URL).getNewsCategoryWise("business")
        call.enqueue(object : Callback<NewsResponseBean> {
            override fun onResponse(
                call: Call<NewsResponseBean>,
                response: Response<NewsResponseBean>
            ) {
                addData(response.body()!!)
            }

            override fun onFailure(call: Call<NewsResponseBean>, t: Throwable) {

            }

        })
    }

    fun getScienceNews(){

        val call = Utilities.create().initWebServiceCall(BuildConfig.BASE_URL).getNewsCategoryWise("science")
        call.enqueue(object : Callback<NewsResponseBean> {
            override fun onResponse(
                call: Call<NewsResponseBean>,
                response: Response<NewsResponseBean>
            ) {
                addData(response.body()!!)
            }

            override fun onFailure(call: Call<NewsResponseBean>, t: Throwable) {

            }

        })
    }

    fun getHealthNews(){

        val call = Utilities.create().initWebServiceCall(BuildConfig.BASE_URL).getNewsCategoryWise("health")
        call.enqueue(object : Callback<NewsResponseBean> {
            override fun onResponse(
                call: Call<NewsResponseBean>,
                response: Response<NewsResponseBean>
            ) {
                addData(response.body()!!)
            }

            override fun onFailure(call: Call<NewsResponseBean>, t: Throwable) {

            }

        })
    }

}