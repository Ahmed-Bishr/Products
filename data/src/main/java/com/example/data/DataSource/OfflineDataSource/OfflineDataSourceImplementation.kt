// OfflineDataSourceImplementation.kt
package com.example.data.Repository.DataSource.OfflineDataSource

import com.example.data.Repository.DataBase.ProductDataBase
import com.example.data.model.ProductsItem
import com.example.data.model.ReviewsItem
import com.example.domain.API.Repository.OfflineDatabase
import com.example.domain.API.entities.ProductsDataItemDTO
import javax.inject.Inject

class OfflineDataSourceImplementation @Inject constructor(
    private val dataItem: ProductDataBase
) : OfflineDatabase {
    override suspend fun saveDatabase(products: List<ProductsDataItemDTO>) {
        return try {
            dataItem.getProductDao().saveData(products.map {
                ProductsItem(
                    it.idKey,
                    it.images,
                    it.thumbnail,
                    it.minimumOrderQuantity,
                    it.rating,
                    it.returnPolicy,
                    it.description,
                    it.weight,
                    it.warrantyInformation,
                    it.title,
                    it.tags,
                    it.discountPercentage,
                    it.reviews.map { review -> ReviewsItem(review.date, review.reviewerName, review.reviewerEmail, review.rating, review.comment) },
                    it.price,
                    it.shippingInformation,
                    it.id,
                    it.availabilityStatus,
                    it.category,
                    it.stock,
                    it.sku,
                    it.brand ?:"",
                    )
            })
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getDatabase(): List<ProductsDataItemDTO> {
        return try {
            dataItem.getProductDao().getData().map { it.toDTO() }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun clearDatabase() {
        return try {
            dataItem.getProductDao().deleteAllData()
        } catch (e: Exception) {
            throw e
        }
    }
}
