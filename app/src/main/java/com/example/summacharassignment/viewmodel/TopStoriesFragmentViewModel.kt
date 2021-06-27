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
                TabItemsModel.TOPSTORIES.id -> this.getTopHeadlines(TabItemsModel.TOPSTORIES.category)

                TabItemsModel.SPORTS.id -> this.getCategoryWiseNews(TabItemsModel.SPORTS.category)

                TabItemsModel.BUSINESS.id -> this.getCategoryWiseNews(TabItemsModel.BUSINESS.category)

                TabItemsModel.SCIENCE.id -> this.getCategoryWiseNews(TabItemsModel.SCIENCE.category)

                TabItemsModel.HEALTH.id -> this.getCategoryWiseNews(TabItemsModel.HEALTH.category)
            }

    }

    private fun getTopHeadlines(category: String){

        val call = Utilities.create().initWebServiceCall(BuildConfig.BASE_URL).getTopHeadlines(category)
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

    private fun getCategoryWiseNews(category:String){

        val call = Utilities.create().initWebServiceCall(BuildConfig.BASE_URL).getNewsCategoryWise(category)
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