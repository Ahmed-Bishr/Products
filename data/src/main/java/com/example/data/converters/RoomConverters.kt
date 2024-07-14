package com.example.data.converters

import androidx.room.TypeConverter

class RoomConverters {

    @TypeConverter
    fun fromIntList(value: List<Int>): String {
        return value.joinToString(",")
    }

    @TypeConverter
    fun toIntList(value: String): List<Int> {
        return value.split(",").map { it.toInt() }
    }
}
