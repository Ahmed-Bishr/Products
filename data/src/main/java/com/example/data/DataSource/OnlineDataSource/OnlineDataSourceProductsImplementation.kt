// OnlineDataSourceProductsImplementation.kt

package com.example.data.Repository.DataSource.OnlineDataSource

import com.example.data.API.Service
import com.example.domain.API.Repository.ProductsOnlineDataSource
import com.example.domain.API.entities.ProductsDataItemDTO
import javax.inject.Inject

class OnlineDataSourceProductsImplementation @Inject constructor(
    private val productService: Service
) : ProductsOnlineDataSource {
    override suspend fun fetchProducts(skip: Int, limit: Int): List<ProductsDataItemDTO> {
        return try {
            productService.fetchProducts(skip, limit).products.map { it.toDTO() }
        } catch (e: Exception) {
            throw e
        }
    }
}
