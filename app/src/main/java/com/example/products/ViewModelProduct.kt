package com.example.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.API.UseCase.GetProductsUseCase
import com.example.domain.API.entities.ProductsDataItemDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelProduct @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductsDataItemDTO>>(emptyList())
    val products: StateFlow<List<ProductsDataItemDTO>> = _products

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private var currentPage = 0
    private val limit = 30

    fun fetchProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            val allProducts = mutableListOf<ProductsDataItemDTO>()

            try {
                while (true) {
                    val response = getProductsUseCase.invoke(currentPage * limit, limit)
                    Log.d("ViewModelProduct", "Fetched ${response.size} items from page $currentPage")
                    allProducts.addAll(response)

                    if (response.size < limit) {
                        // If the response has fewer items than the limit, it means we've fetched all pages
                        break
                    }

                    currentPage++
                }

                _products.value = allProducts
                Log.d("ViewModelProduct", "Total products fetched: ${allProducts.size}")
            } catch (e: Exception) {
                _error.value = "Failed to fetch products: ${e.message}"
                Log.e("ViewModelProduct", "Failed to fetch products: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refreshProducts() {
        viewModelScope.launch {
            _isRefreshing.value = true
            _error.value = null
            currentPage = 0 // Reset the current page
            val allProducts = mutableListOf<ProductsDataItemDTO>()

            try {
                while (true) {
                    val response = getProductsUseCase.invoke(currentPage * limit, limit)
                    Log.d("ViewModelProduct", "Fetched ${response.size} items from page $currentPage")
                    allProducts.addAll(response)

                    if (response.size < limit) {
                        // If the response has fewer items than the limit, it means we've fetched all pages
                        break
                    }

                    currentPage++
                }

                _products.value = allProducts
                Log.d("ViewModelProduct", "Total products fetched: ${allProducts.size}")
            } catch (e: Exception) {
                _error.value = "Failed to fetch products: ${e.message}"
                Log.e("ViewModelProduct", "Failed to fetch products: ${e.message}")
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}
