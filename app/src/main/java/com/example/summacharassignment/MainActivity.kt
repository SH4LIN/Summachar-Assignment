package com.example.summacharassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.summacharassignment.adapter.ViewPagerAdapterScreen
import com.example.summacharassignment.utils.TabItemsModel
import com.example.summacharassignment.utils.Utilities
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utilities.create().changeStatusBarColor(this, R.color.primary_color)
        setContentView(R.layout.activity_main)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val view_pager_screen = findViewById<ViewPager2>(R.id.view_pager_screen)
        view_pager_screen.offscreenPageLimit = 5
        view_pager_screen.isUserInputEnabled = false
        val viewPagerAdapter = ViewPagerAdapterScreen(this,supportFragmentManager,lifecycle)
        view_pager_screen.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, view_pager_screen) { tab, position ->
            tab.text = getString(TabItemsModel.values()[position].title)
        }.attach()
    }


}