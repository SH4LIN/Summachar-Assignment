package com.example.summacharassignment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.summacharassignment.R
import com.example.summacharassignment.utils.AppConstant
import me.relex.circleindicator.CircleIndicator3
import java.text.SimpleDateFormat
import java.util.*

class NewsRecyclerAdapter(val title: String?): RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {
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
        val netDate = AppConstant.instance?.map?.get(title)?.articles?.get(position)?.publishedAt?.let {
            Date(
                it.time
            )
        }
        val date = sdf.format(netDate)
        val currentDate = sdf.format(Date())

        val dateToPrinted = if(currentDate.equals(date)) "Today" else date
        holder.newsTime.text = dateToPrinted
    }

    private fun displayImages(holder: ViewHolder,position: Int){
        Log.d("Image", AppConstant.instance!!.map.get(title)?.articles?.get(position)?.urlToImage.toString())
        val multiImageSliderAdapter = MultiImageSliderAdapter(AppConstant.instance!!.map.get(title)?.articles?.get(position)?.urlToImage)
        holder.viewPagerMultiImage.adapter = multiImageSliderAdapter
        holder.viewPagerMultiImage.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        holder.circleIndicator.setViewPager(holder.viewPagerMultiImage)
    }

    override fun getItemCount(): Int {
        return if(AppConstant.instance?.map?.get(title)?.articles?.size == null) 0 else AppConstant.instance?.map?.get(title)?.articles?.size!!
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsTime:TextView = itemView.findViewById(R.id.newsTime)
        val viewPagerMultiImage: ViewPager2 = itemView.findViewById(R.id.viewPagerMultiImage)
        val circleIndicator:CircleIndicator3 = itemView.findViewById(R.id.circleIndicator)
    }
}