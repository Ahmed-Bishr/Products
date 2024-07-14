// ProductsDataItemDTO.kt
package com.example.domain.API.entities

data class ProductsDataItemDTO(
    val idKey : Int?,
    val images: List<String>, // Change type to List<String>
    val thumbnail: String,
    val minimumOrderQuantity: Int,
    val rating: Double,
    val returnPolicy: String,
    val description: String,
    val weight: Int,
    val warrantyInformation: String,
    val title: String,
    val tags: List<String>,
    val discountPercentage: Double,
    val reviews: List<ReviewDTO>,
    val price: Double,
    val shippingInformation: String,
    val id: Int,
    val availabilityStatus: String,
    val category: String,
    val stock: Int,
    val sku: String,
    val brand: String?
)
