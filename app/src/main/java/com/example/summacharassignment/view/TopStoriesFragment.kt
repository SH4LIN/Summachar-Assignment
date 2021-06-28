package com.example.summacharassignment.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summacharassignment.R
import com.example.summacharassignment.adapter.NewsRecyclerAdapter
import com.example.summacharassignment.model.NewsResponseBean
import com.example.summacharassignment.viewmodel.TopStoriesFragmentViewModel

class TopStoriesFragment: Fragment(), View.OnClickListener {
    private var title: String = ""
    private var page = 0
    private lateinit var materialButtonRelativeLayout: RelativeLayout
    private lateinit var buttonMorePost: Button
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var  newsRecyclerAdapter: NewsRecyclerAdapter
    private lateinit var  internetConnectionError: TextView
    private var newsData: NewsResponseBean = NewsResponseBean()
    private var flag:Boolean = false
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
                scrollToPosition(0)
            }else{
                internetConnectionError.visibility = View.VISIBLE
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.top_stories_fragment,container,false)

        setViews(view)
        setRecyclerView()
        setListener()

        return view
    }

    private fun setRecyclerView(){
        newsRecyclerView.layoutManager = LinearLayoutManager(this.context)
        newsRecyclerAdapter = NewsRecyclerAdapter(this.context,newsData)
        newsRecyclerView.adapter = newsRecyclerAdapter
        /*newsRecyclerView.addOnScrollListener(this)*/
    }

    private fun setListener(){
        buttonMorePost.setOnClickListener(this)
    }

    private fun setViews(view: View){
        materialButtonRelativeLayout = view.findViewById(R.id.materialButtonRelativeLayout)
        buttonMorePost = view.findViewById(R.id.buttonMorePost)
        newsRecyclerView = view.findViewById(R.id.newsRecyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        internetConnectionError = view.findViewById(R.id.internetConnectionError)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        if(!this.flag){
            progressBar.visibility = View.VISIBLE
            materialButtonRelativeLayout.visibility = View.GONE
            model.checkData(page,false)
        }
    }

    private fun scrollToPosition(position:Int){
        newsRecyclerView.smoothScrollToPosition(position)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                buttonMorePost.id -> {
                    progressBar.visibility = View.VISIBLE
                    materialButtonRelativeLayout.visibility = View.GONE
                    model.checkData(page,true)
                }
            }
        }
    }
}