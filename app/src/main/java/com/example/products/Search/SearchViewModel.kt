package com.example.products.Search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.API.entities.ProductsDataItemDTO

class SearchViewModel : ViewModel() {
    // Mutable state for holding the current search query
    val searchQuery: MutableState<String> = mutableStateOf("")

    // Function to update the search query
    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }

    // Function to filter products based on the current search query
    fun filterProducts(products: List<ProductsDataItemDTO>): List<ProductsDataItemDTO> {
        val query = searchQuery.value.trim().lowercase()
        if (query.isEmpty()) {
            return products
        }
        return products.filter { product ->
            product.title.lowercase().contains(query)
        }
    }
}
