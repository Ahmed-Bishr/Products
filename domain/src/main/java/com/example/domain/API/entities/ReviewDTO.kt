package com.example.domain.API.entities

data class ReviewDTO(
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String,
    val rating: Int,
    val comment: String

)
