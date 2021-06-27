package com.example.summacharassignment.utils

import com.example.summacharassignment.R

enum class TabItemsModel(val title: Int, val id:Int,val category:String){
    TOPSTORIES(R.string.top_headlines,1,"in"),
    SPORTS(R.string.sports,2,"sports"),
    BUSINESS(R.string.business,3,"business"),
    SCIENCE(R.string.science,4,"science"),
    HEALTH(R.string.health,5,"health"),
}