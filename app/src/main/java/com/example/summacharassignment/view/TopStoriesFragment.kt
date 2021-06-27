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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summacharassignment.R
import com.example.summacharassignment.adapter.NewsRecyclerAdapter
import com.example.summacharassignment.network.ApiResponseHandler
import com.example.summacharassignment.model.NewsResponseBean
import com.example.summacharassignment.utils.AppConstant
import com.example.summacharassignment.viewmodel.TopStoriesFragmentViewModel
import retrofit2.Response

class TopStoriesFragment: Fragment(), ApiResponseHandler, View.OnClickListener {
    private var title: String = ""
    private var page = 0
    lateinit var materialButtonRelativeLayout: RelativeLayout
    lateinit var buttonMorePost: Button
    lateinit var newsRecyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var  newsRecyclerAdapter: NewsRecyclerAdapter
    var newsData: NewsResponseBean = NewsResponseBean()
    var flag:Boolean = false
    private val model: TopStoriesFragmentViewModel by viewModels()

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
        page = arguments?.getInt("id", 0)!!
        title = arguments?.getString("title").toString()
        model.getDataObserver().observe(this, {
            if(it != null){
                this.flag = true
                this.newsData = it
                newsRecyclerAdapter.setData(it)
                progressBar.visibility = View.GONE
                materialButtonRelativeLayout.visibility = View.VISIBLE
            }
        })
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
        newsRecyclerAdapter = NewsRecyclerAdapter(this.context,newsData)
        newsRecyclerView.adapter = newsRecyclerAdapter

        progressBar = view.findViewById(R.id.progressBar)
        return view
    }

    override fun onResume() {
        super.onResume()
        if(!this.flag){
            progressBar.visibility = View.VISIBLE
            materialButtonRelativeLayout.visibility = View.GONE
            model.callApi(page)
        }
    }


    override fun onApiSuccess(response: Response<NewsResponseBean>) {
        AppConstant.instance?.addData(title, response.body()!!)
        newsRecyclerAdapter.notifyDataSetChanged()
        progressBar.visibility = View.GONE
        materialButtonRelativeLayout.visibility = View.VISIBLE
        scrollToPosition(0)
    }

    private fun scrollToPosition(position:Int){
        newsRecyclerView.smoothScrollToPosition(position)
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
                    progressBar.visibility = View.VISIBLE
                    materialButtonRelativeLayout.visibility = View.GONE
                    model.callApi(page)
                }
            }
        }
    }
}