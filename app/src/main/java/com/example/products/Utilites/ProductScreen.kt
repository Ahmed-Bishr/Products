package com.example.products.Utilites

import UpperSides
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.products.Search.SearchViewModel
import com.example.products.ViewModelProduct
import com.example.products.ui.theme.blue
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ProductScreen(
    viewModel: ViewModelProduct,
    searchViewModel: SearchViewModel,
    modifier: Modifier = Modifier
) {
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    Scaffold(topBar = {
        UpperSides(
            searchText = searchViewModel.searchQuery.value,
            onSearchTextChanged = { searchViewModel.setSearchQuery(it) },
            onSearchClicked = { },
            onClearClicked = { searchViewModel.setSearchQuery("") },
            modifier = Modifier.fillMaxWidth()
        )
    }) { paddingValues ->
        val products by viewModel.products.collectAsState()
        val isLoading by viewModel.isLoading.collectAsState()

        val filteredProducts = searchViewModel.filterProducts(products)

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.refreshProducts() },
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    contentColor = blue // Set your custom color here
                )
            }
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = blue)
                }
            } else {
                LazyColumn(modifier = modifier.padding(paddingValues)) {
                    if (filteredProducts.isEmpty()) {
                        item {
                            Text(
                                text = "No items found!!",
                                modifier = Modifier.fillMaxSize().padding(top = 20.dp),
                                color = Color.Red,
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        items(filteredProducts.chunked(2)) { rowItems ->
                            Row(modifier = Modifier.fillMaxWidth()) {
                                rowItems.forEachIndexed { index, product ->
                                    ProductBox(
                                        product = product,
                                        isLoading = isLoading,
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        CardViewProduct(product = product)
                                    }
                                    if (index < rowItems.size - 1) {
                                        Spacer(modifier = Modifier.width(8.dp)) // Adjust spacing as needed
                                    }
                                }
                                if (rowItems.size < 2) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
