import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecommerceapplication.UIComponents.Utilites.utils.RouteImage
import com.example.myshop.presentation.utils.CartIcon
import com.example.products.Search.SearchBar
import com.example.products.Search.SearchViewModel

@Composable
fun UpperSides(
    modifier: Modifier = Modifier,
    searchText: String,
    onSearchTextChanged: (String) -> Unit, // Changed to accept String parameter
    onSearchClicked: () -> Unit,
    onClearClicked: () -> Unit
) {
    Column {
        RouteImage() // Assuming this shows a route or header image
        Spacer(modifier = Modifier.padding(top = 5.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            SearchBar(
                searchText = searchText,
                onSearchTextChanged = { onSearchTextChanged(it) },
                onSearchClicked = onSearchClicked,
                onClearClicked = onClearClicked,
                modifier = Modifier.weight(1f)
            )
            CartIcon(modifier = Modifier.size(20.dp)) // Adjust the size as per your icon size requirements
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun UpperSidesPreview() {
    UpperSides(
        searchText = "Your Search Text", // Provide a default search text for preview
        onSearchTextChanged = { /* Handle search text change */ },
        onSearchClicked = { /* Handle search icon click if needed */ },
        onClearClicked = { /* Handle clear icon click if needed */ }
    )
}
