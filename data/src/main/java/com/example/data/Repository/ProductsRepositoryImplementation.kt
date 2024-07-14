package com.example.data.Repository

import com.example.domain.API.Repository.ProductsOnlineDataSource
import com.example.domain.API.Repository.ProductsRepository
import com.example.domain.API.entities.ProductsDataItemDTO
import javax.inject.Inject

class ProductsRepositoryImplementation @Inject constructor(
    private val productsOnlineDataSource: ProductsOnlineDataSource
) : ProductsRepository {
    override suspend fun getProducts(skip: Int, limit: Int): List<ProductsDataItemDTO> {
        return try {
            productsOnlineDataSource.fetchProducts(skip, limit)
        } catch (e: Exception) {
            throw e
        }
    }
}

