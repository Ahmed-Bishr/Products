package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.API.entities.ProductsDataItemDTO
import com.google.gson.annotations.SerializedName

@Entity(tableName = "products")
data class ProductsItem(

    @PrimaryKey(autoGenerate = true)
    var idKey: Int? = null,

    @field:SerializedName("images")
    val images: List<String>, // Change type to List<String>

    @field:SerializedName("thumbnail")
    val thumbnail: String,

    @field:SerializedName("minimumOrderQuantity")
    val minimumOrderQuantity: Int,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("returnPolicy")
    val returnPolicy: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("weight")
    val weight: Int,

    @field:SerializedName("warrantyInformation")
    val warrantyInformation: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("tags")
    val tags: List<String>,

    @field:SerializedName("discountPercentage")
    val discountPercentage: Double,

    @field:SerializedName("reviews")
    val reviews: List<ReviewsItem>,

    @field:SerializedName("price")
    val price: Double,

    @field:SerializedName("shippingInformation")
    val shippingInformation: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("availabilityStatus")
    val availabilityStatus: String,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("stock")
    val stock: Int,

    @field:SerializedName("sku")
    val sku: String,

    @field:SerializedName("brand")
    val brand: String,

    ) {
    fun toDTO(): ProductsDataItemDTO {
        return ProductsDataItemDTO(
            idKey = idKey,
            images = this.images,
            thumbnail = this.thumbnail,
            minimumOrderQuantity = this.minimumOrderQuantity,
            rating = this.rating,
            returnPolicy = this.returnPolicy,
            description = this.description,
            weight = this.weight,
            warrantyInformation = this.warrantyInformation,
            title = this.title,
            tags = this.tags,
            discountPercentage = this.discountPercentage,
            reviews = this.reviews.map { it.ReviewsItemDTO() },
            price = this.price,
            shippingInformation = this.shippingInformation,
            id = this.id,
            availabilityStatus = this.availabilityStatus,
            category = this.category,
            stock = this.stock,
            sku = this.sku,
            brand = this.brand
        )
    }
}
