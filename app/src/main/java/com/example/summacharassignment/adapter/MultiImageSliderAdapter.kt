package com.example.summacharassignment.adapter

import android.net.Uri
import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.summacharassignment.R
import com.facebook.shimmer.ShimmerFrameLayout
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class MultiImageSliderAdapter(image:String?): RecyclerView.Adapter<MultiImageSliderAdapter.ViewHolder>() {
    private var imagesList:List<String>?
    private var flag: Boolean = false
    init {
        if(image != null){
            imagesList = image.split(";")
        }else{
            flag = true
            imagesList = null
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.multi_image_slide, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.shimmerFrameLayout.startShimmer()

        if(flag){
            Picasso.get().load(R.drawable.dummynewsimage).into(holder.imgNews)
            holder.shimmerFrameLayout.stopShimmer()
            holder.shimmerFrameLayout.visibility = View.GONE
        }else {
            val uri = Uri.parse(imagesList!![position])
            Picasso.get().load(uri).into(holder.imgNews, object : Callback {
                override fun onSuccess() {
                    holder.shimmerFrameLayout.stopShimmer()
                    holder.shimmerFrameLayout.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    Picasso.get().load(R.drawable.dummynewsimage).into(holder.imgNews)
                    holder.shimmerFrameLayout.stopShimmer()
                    holder.shimmerFrameLayout.visibility = View.GONE
                }

            })
        }
    }

    override fun getItemCount(): Int {

        return if(flag) 1 else imagesList!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgNews: ImageView = itemView.findViewById(R.id.imgNews)
        val shimmerFrameLayout: ShimmerFrameLayout = itemView.findViewById(R.id.shimmerFrameLayout)
    }
}