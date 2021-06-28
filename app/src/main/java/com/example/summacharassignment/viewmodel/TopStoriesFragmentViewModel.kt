package com.example.summacharassignment.viewmodel

import android.app.Application
import android.os.Build
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.summacharassignment.BuildConfig
import com.example.summacharassignment.data.NewsData
import com.example.summacharassignment.data.NewsDataDatabase
import com.example.summacharassignment.data.NewsDataRepository
import com.example.summacharassignment.model.NewsResponseBean
import com.example.summacharassignment.utils.TabItemsModel
import com.example.summacharassignment.utils.Utilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopStoriesFragmentViewModel(application: Application) : AndroidViewModel(application) {


    private val data = MutableLiveData<NewsResponseBean>()
    private val TAG: String = javaClass.simpleName
    fun getDataObserver(): MutableLiveData<NewsResponseBean> {
        return this.data
    }

    private val repository:NewsDataRepository

    init {
        val newsDataDao = NewsDataDatabase.getDatabase(application).newsDataDao()
        repository = NewsDataRepository(newsDataDao)
    }


    //DB OPERATION
    fun addData(response: NewsResponseBean,pageId: Int){
        addNewsToDB(response,pageId)
    }

    private fun addNewsToDB(data: NewsResponseBean, pageId: Int){
        viewModelScope.launch(Dispatchers.IO){
            data.articles.forEach {
                val newsData = NewsData(
                    title = it.title,
                    urlToImage = it.urlToImage,
                    url = it.url,
                    publishedAt = it.publishedAt,
                    author = it.author,
                    content = it.content,
                    description = it.description,
                    sourceId = it.source.id,
                    sourceName = it.source.name,
                    tagId = pageId,
                    id = 0
                )
                if(!repository.isRowExist(pageId,it.title,it.url,it.publishedAt)) {
                    /*Log.d(TAG,"Data Added To The Database")*/
                    repository.adduser(newsData)
                }
            }
            getDataFromDB(pageId)
        }
    }

    fun getDataFromDB(pageId: Int){
        viewModelScope.launch(Dispatchers.IO){
            val dbData = repository.filterNewsByTagId(pageId)
            val newsResponseBean = NewsResponseBean()
            dbData.forEach {
                val article = NewsResponseBean.Articles()
                article.source.id = it.sourceId.toString()
                article.source.name = it.sourceName
                article.content = it.content.toString()
                article.url = it.url
                article.urlToImage = it.urlToImage.toString()
                article.author = it.author.toString()
                article.description = it.description.toString()
                article.publishedAt = it.publishedAt
                article.title = it.title
                newsResponseBean.articles.add(article)
            }
            data.postValue(newsResponseBean)
        }
    }

    private fun deleteNewsFromDB(pageId: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllDataOfPageId(pageId)
        }
    }

    //DB OPERATION ENDS HERE

    //API CALLS

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkData(page: Int, flag:Boolean){
        var count: Int
        viewModelScope.launch(Dispatchers.IO){
            count = repository.checkDataExistForTag(page)
            Log.d(TAG,"Count $count")
            if(count > 0) {
                if (Utilities.create().checkConnection(getApplication())) {
                    if (flag) {
                        /*Log.d(TAG,"Data Added From API AND DB")*/
                        callApi(page)
                    }
                    /*Log.d(TAG,"Data Added From DB")*/
                    getDataFromDB(page)
                } else {
                    /*Log.d(TAG,"Data Added From Only DB")*/
                    getDataFromDB(page)
                    withContext(Dispatchers.Main){
                        val toast = Toast.makeText(
                            getApplication(),
                            "Could Not Refresh The News\nPlease Check Your Internet Connection",
                            Toast.LENGTH_LONG
                        )
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                    }


                }
            }else{
                if (Utilities.create().checkConnection(getApplication())) {
                    /*Log.d(TAG,"Data Added From Only API")*/
                    callApi(page)
                }else{
                    data.postValue(NewsResponseBean())
                    withContext(Dispatchers.Main){
                        val toast = Toast.makeText(
                            getApplication(),
                            "Please Check Your Internet Connection",
                            Toast.LENGTH_LONG
                        )
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                    }
                }
            }
        }
    }


    private fun callApi(page:Int){
        when (page) {
            TabItemsModel.TOPSTORIES.id -> this.getTopHeadlines(
                TabItemsModel.TOPSTORIES.category,
                page
            )

            TabItemsModel.SPORTS.id -> this.getCategoryWiseNews(
                TabItemsModel.SPORTS.category,
                page
            )

            TabItemsModel.BUSINESS.id -> this.getCategoryWiseNews(
                TabItemsModel.BUSINESS.category,
                page
            )

            TabItemsModel.SCIENCE.id -> this.getCategoryWiseNews(
                TabItemsModel.SCIENCE.category,
                page
            )

            TabItemsModel.HEALTH.id -> this.getCategoryWiseNews(
                TabItemsModel.HEALTH.category,
                page
            )
        }
    }

    private fun getTopHeadlines(category: String,pageId: Int){

        val call = Utilities.create().initWebServiceCall(BuildConfig.BASE_URL).getTopHeadlines(category)
        call.enqueue(object : Callback<NewsResponseBean> {
            override fun onResponse(
                call: Call<NewsResponseBean>,
                response: Response<NewsResponseBean>
            ) {
                addData(response.body()!!,pageId)
            }

            override fun onFailure(call: Call<NewsResponseBean>, t: Throwable) {
                getDataFromDB(pageId)
            }

        })
    }

    private fun getCategoryWiseNews(category:String,pageId: Int){

        val call = Utilities.create().initWebServiceCall(BuildConfig.BASE_URL).getNewsCategoryWise(category)
        call.enqueue(object : Callback<NewsResponseBean> {
            override fun onResponse(
                call: Call<NewsResponseBean>,
                response: Response<NewsResponseBean>
            ) {
                addData(response.body()!!,pageId)
            }

            override fun onFailure(call: Call<NewsResponseBean>, t: Throwable) {
                getDataFromDB(pageId)
            }

        })
    }
    //API CALLS ENDS HERE

}