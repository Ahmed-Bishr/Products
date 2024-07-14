package com.example.domain.API.UseCase

import com.example.domain.API.Repository.ProductsRepository
import com.example.domain.API.entities.ProductsDataItemDTO
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductsRepository
) {
    suspend fun invoke(skip: Int, limit: Int): List<ProductsDataItemDTO> {
        return productRepository.getProducts(skip, limit)
    }

}


