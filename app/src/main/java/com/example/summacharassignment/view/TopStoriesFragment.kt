package com.example.summacharassignment.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summacharassignment.R
import com.example.summacharassignment.adapter.NewsRecyclerAdapter
import com.example.summacharassignment.interfc.ApiResponseHandler
import com.example.summacharassignment.model.NewsResponseBean
import com.example.summacharassignment.presenter.GetNewsPresenter
import com.example.summacharassignment.utils.AppConstant
import com.example.summacharassignment.utils.TabItemsModel
import retrofit2.Response

class TopStoriesFragment: Fragment(),ApiResponseHandler, View.OnClickListener {
    private var title: String? = null
    private var page = 0
    lateinit var materialButtonRelativeLayout: RelativeLayout
    lateinit var buttonMorePost: Button
    lateinit var newsRecyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    var flag: Boolean = false
    var getNewsPresenter = GetNewsPresenter()
    lateinit var  newsRecyclerAdapter: NewsRecyclerAdapter

    companion object{
        fun newInstance(id:Int,title:String): TopStoriesFragment{
            val args = Bundle()
            args.putInt("id",id)
            args.putString("title",title)
            val fragment = TopStoriesFragment()
            fragment.arguments = args
            return fragment
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments?.getInt("id", 0)!!;
        title = arguments?.getString("title");
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.top_stories_fragment,container,false)
        materialButtonRelativeLayout = view.findViewById(R.id.materialButtonRelativeLayout)
        buttonMorePost = view.findViewById(R.id.buttonMorePost)

        buttonMorePost.setOnClickListener(this)

        newsRecyclerView = view.findViewById(R.id.newsRecyclerView)
        newsRecyclerView.layoutManager = LinearLayoutManager(this.context)
        newsRecyclerAdapter = NewsRecyclerAdapter(title)
        newsRecyclerView.adapter = newsRecyclerAdapter

        progressBar = view.findViewById(R.id.progressBar)
        return view
    }

    override fun onResume() {
        super.onResume()
        callApi()
        Log.d("Fragment Title", title.toString())
    }

    fun callApi(){
        if(!this.flag){
            progressBar.visibility = View.VISIBLE
            materialButtonRelativeLayout.visibility = View.GONE
            when(page){
                TabItemsModel.TOPSTORIES.id -> this.getNewsPresenter.getTopHeadlines(this)

                TabItemsModel.SPORTS.id -> this.getNewsPresenter.getSportsNews(this)

                TabItemsModel.BUSINESS.id -> this.getNewsPresenter.getBusinessNews(this)

                TabItemsModel.SCIENCE.id -> this.getNewsPresenter.getScienceNews(this)

                TabItemsModel.HEALTH.id -> this.getNewsPresenter.getHealthNews(this)
            }
            flag = true
        }
    }

    override fun onApiSuccess(response: Response<NewsResponseBean>) {
        progressBar.visibility = View.GONE
        materialButtonRelativeLayout.visibility = View.VISIBLE
        val map = AppConstant.instance!!.map
        map["$title"] = response.body()
        AppConstant.instance?.map = map
        newsRecyclerAdapter.notifyDataSetChanged()
    }

    override fun onApiFailure(t: Throwable) {
        progressBar.visibility = View.GONE
        materialButtonRelativeLayout.visibility = View.VISIBLE
        Log.e("Api Error: ",t.toString())
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                buttonMorePost.id -> {
                    this.flag = false
                    callApi()
                }
            }
        }
    }
}