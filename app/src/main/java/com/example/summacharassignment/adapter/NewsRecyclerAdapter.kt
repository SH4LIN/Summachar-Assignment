package com.example.summacharassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.summacharassignment.R
import com.example.summacharassignment.model.NewsResponseBean
import me.relex.circleindicator.CircleIndicator3
import java.text.SimpleDateFormat
import java.util.*

class NewsRecyclerAdapter(private val mContext: Context?, private var newsData: NewsResponseBean): RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        printDate(holder,position)
        displayImages(holder,position)

    }

    private fun printDate(holder: ViewHolder, position: Int){
        val sdf = SimpleDateFormat("dd/MM/yy")

        val netDate =  newsData.articles[position].publishedAt?.time
        val date = sdf.format(netDate)
        val currentDate = sdf.format(Date())

        val dateToPrinted = if(currentDate.equals(date)) "Today" else date
        holder.newsTime.text = dateToPrinted
    }

    private fun displayImages(holder: ViewHolder,position: Int){
        val multiImageSliderAdapter = MultiImageSliderAdapter(newsData.articles[position].urlToImage)
        holder.viewPagerMultiImage.adapter = multiImageSliderAdapter
        holder.viewPagerMultiImage.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        holder.circleIndicator.setViewPager(holder.viewPagerMultiImage)
    }

    override fun getItemCount(): Int {
        return if (newsData.articles == null) 0 else newsData.articles.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsTime:TextView = itemView.findViewById(R.id.newsTime)
        val viewPagerMultiImage: ViewPager2 = itemView.findViewById(R.id.viewPagerMultiImage)
        val circleIndicator:CircleIndicator3 = itemView.findViewById(R.id.circleIndicator)
    }

    fun setMoreData(dataToAdd:NewsResponseBean){
        this.newsData.totalResults = this.newsData.totalResults + dataToAdd.totalResults
        this.newsData.articles.addAll(dataToAdd.articles)
    }

    fun setData(dataToAdd: NewsResponseBean){
        this.newsData = dataToAdd
        notifyDataSetChanged()
    }
}