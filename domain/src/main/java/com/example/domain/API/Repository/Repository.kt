package com.example.domain.API.Repository

import com.example.domain.API.entities.ProductsDataItemDTO

interface ProductsRepository {
    suspend fun getProducts(skip: Int, limit: Int): List<ProductsDataItemDTO>
}

interface OfflineDatabase {
    suspend fun saveDatabase(products: List<ProductsDataItemDTO>)
    suspend fun getDatabase(): List<ProductsDataItemDTO>
    suspend fun clearDatabase()

}

interface ProductsOnlineDataSource{
    suspend fun fetchProducts(skip: Int, limit: Int): List<ProductsDataItemDTO>
}