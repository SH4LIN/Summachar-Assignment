package com.example.summacharassignment.utils

class AppConstant private constructor(){
    companion object{
        private var INSTANCE: AppConstant? = null

        val instance: AppConstant?
            get() {
                if (INSTANCE == null) {
                    synchronized(AppConstant::class.java) {
                        if (INSTANCE == null) {
                            INSTANCE =
                                AppConstant()
                        }
                    }
                }
                return INSTANCE
            }
    }
}