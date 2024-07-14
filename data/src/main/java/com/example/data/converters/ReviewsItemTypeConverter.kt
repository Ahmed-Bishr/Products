package com.example.data.converters

import androidx.room.TypeConverter
import com.example.data.model.ReviewsItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ReviewsItemTypeConverter {

    @TypeConverter
    fun fromReviewsItemList(reviews: List<ReviewsItem>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ReviewsItem>>() {}.type
        return gson.toJson(reviews, type)
    }

    @TypeConverter
    fun toReviewsItemList(reviewsString: String): List<ReviewsItem> {
        val gson = Gson()
        val type = object : TypeToken<List<ReviewsItem>>() {}.type
        return gson.fromJson(reviewsString, type)
    }
}
