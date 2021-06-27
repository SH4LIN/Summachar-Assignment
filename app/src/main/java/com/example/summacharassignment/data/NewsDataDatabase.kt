package com.example.summacharassignment.data

import android.content.Context
import androidx.room.*

@Database(entities = [NewsData::class], version = 1)
@TypeConverters(Converters::class)
abstract class NewsDataDatabase: RoomDatabase() {
    abstract fun newsDataDao(): NewsDataDao

    companion object{
        @Volatile
        private var INSTANCE: NewsDataDatabase? = null

        fun getDatabase(context: Context): NewsDataDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDataDatabase::class.java,
                    "News"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}