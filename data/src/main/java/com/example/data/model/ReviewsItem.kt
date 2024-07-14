package com.example.data.model

import com.example.domain.API.entities.ReviewDTO
import com.google.gson.annotations.SerializedName

class ReviewsItem(

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("reviewerName")
    val reviewerName: String,

    @field:SerializedName("reviewerEmail")
    val reviewerEmail: String,

    @field:SerializedName("rating")
    val rating: Int,

    @field:SerializedName("comment")
    val comment: String
) {
    fun ReviewsItemDTO(): ReviewDTO {
        return ReviewDTO(
            date,
            reviewerName,
            reviewerEmail,
            rating,
            comment
        )
    }
}