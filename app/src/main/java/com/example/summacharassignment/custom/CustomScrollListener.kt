package com.example.summacharassignment.custom

import androidx.recyclerview.widget.RecyclerView

class CustomScrollListener: RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        when {
            dy > 0 -> {
                System.out.println("Scrolled Downwards");
            }
            dy < 0 -> {
                System.out.println("Scrolled Upwards");
            }
            else -> {
                System.out.println("No Vertical Scrolled");
            }
        }
    }
}