package com.example.data.model

import com.google.gson.annotations.SerializedName

 class ResponseProducts(

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("limit")
	val limit: Int,

	@field:SerializedName("skip")
	val skip: Int,

	@field:SerializedName("products")
	val products: List<ProductsItem>
)