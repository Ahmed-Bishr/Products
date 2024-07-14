package com.example.data.API

import com.example.data.model.ResponseProducts
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("products")
    suspend fun fetchProducts(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): ResponseProducts
}
