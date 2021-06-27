package com.example.summacharassignment.data

import androidx.room.TypeConverter
import java.sql.Timestamp

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Timestamp? {
        return value?.let { Timestamp(value) }
    }

    @TypeConverter
    fun dateToTimestamp(timestamp: Timestamp): Long? {
        return timestamp.time
    }
}