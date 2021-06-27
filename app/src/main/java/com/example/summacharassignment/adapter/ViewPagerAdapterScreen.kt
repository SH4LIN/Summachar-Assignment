package com.example.summacharassignment.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.summacharassignment.utils.TabItemsModel
import com.example.summacharassignment.view.TopStoriesFragment

class ViewPagerAdapterScreen(private val mContext: Context,private val fManager:FragmentManager,private val lifecycle: Lifecycle): FragmentStateAdapter(fManager,lifecycle) {

    override fun getItemCount(): Int = TabItemsModel.values().size

    override fun createFragment(position: Int): Fragment {
        return TopStoriesFragment.newInstance(TabItemsModel.values()[position].id,mContext.getString(TabItemsModel.values()[position].title))
    }

}