package com.example.products.Utilites

import android.icu.text.DecimalFormat
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.domain.API.entities.ProductsDataItemDTO
import com.example.products.R
import com.example.products.ui.theme.blue
import com.example.products.ui.theme.blue2
import com.example.products.ui.theme.blueBorder

@Composable
fun CardViewProduct(product: ProductsDataItemDTO?, modifier: Modifier = Modifier) {
    Column {
        ImageView(product = product)
        ProductDetails(product = product)
    }
}

@Composable
fun ProductBox(
    product: ProductsDataItemDTO?,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .wrapContentHeight()
            .width(200.dp)
            .padding(15.dp)
            .border(2.dp, color = blueBorder, shape = RoundedCornerShape(12.dp))
            .clip(
                RoundedCornerShape(
                    topStart = 9.dp,
                    topEnd = 9.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
    ) {
        if (isLoading || product == null) {
            // Placeholder content for loading state or when product is null
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = blue)
            }
        } else {
            // Actual content when product data is available
            content()
        }
    }
}


@Composable
fun IconLike(modifier: Modifier = Modifier) {
    val clicked = remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current

    Card(
        modifier = modifier
            .size(30.dp)
            .padding(5.dp)
            .shadow(6.dp, CircleShape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    clicked.value = !clicked.value
                    if (clicked.value) {
                        Toast
                            .makeText(context, "Item added to WishList", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast
                            .makeText(context, "Item Removed from WishList", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            ),
        shape = CircleShape
    ) {
        Icon(
            painter = painterResource(id = if (clicked.value) R.drawable.whishlist else R.drawable.png),
            contentDescription = "icon",
            modifier = Modifier.padding(3.dp),
            tint = blue
        )
    }
}

@Composable
fun ImageView(product: ProductsDataItemDTO?, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .clip(
                RoundedCornerShape(
                    topStart = 9.dp,
                    topEnd = 9.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .background(Color.White)
    ) {
        if (product != null) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth() // Fill the width of the parent Box
                    .wrapContentHeight(), // Wrap content height as before
                contentScale = ContentScale.FillWidth // Ensure the image fills the width
            )
        }
        // Position IconLike at the top-left corner
        IconLike(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 5.dp)
        )
    }
}

@Composable
fun ProductDetails(product: ProductsDataItemDTO?, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(start = 6.dp, end = 3.dp)) {
        ProductTitle(product)
        Spacer(modifier = Modifier.height(2.5.dp))
        ProductPrice(product)
        Spacer(modifier = Modifier.height(2.5.dp))
        ProductReview(product)
    }
}

@Composable
fun ProductTitle(product: ProductsDataItemDTO?) {
    Text(
        text = product?.title ?: "Default Product Name",
        maxLines = 2, // Limit to two lines
        overflow = TextOverflow.Ellipsis, // Handle overflow with ellipsis
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        color = blue
    )
}

@Composable
fun ProductPrice(product: ProductsDataItemDTO?) {
    Row {
        if (product != null) {
            val discount = product.discountPercentage / 100.0
            val price = product.price
            val discountAmount = price * discount
            val finalPrice = price - discountAmount

            val decimalFormat = DecimalFormat("0.00")
            val formattedFinalPrice = decimalFormat.format(finalPrice)
            Text(
                text = "EGP $formattedFinalPrice",
                fontSize = 13.sp,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                color = blue,
            )
            Spacer(modifier = Modifier.width(7.dp))
            Text(
                text = "EGP ${decimalFormat.format(price)}",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                maxLines = 1,
                color = blue2,
                textDecoration = TextDecoration.LineThrough
            )
        } else {
            Text(
                text = "EGP 0",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                maxLines = 1,
                color = blue,
            )
        }
    }
}


@Composable
fun ProductReview(product: ProductsDataItemDTO?, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ReviewText(product)
        Spacer(modifier = Modifier.width(2.5.dp))
        ReviewImage()
        Spacer(modifier = Modifier.width(8.dp)) // Adjust spacer as needed
        ReviewIcon()
    }
}

@Composable
fun ReviewText(product: ProductsDataItemDTO?) {
    Text(
        text = "Reviews  ${product?.rating}",
        fontWeight = FontWeight.Bold,
        color = blue,
        fontSize = 13.sp,
        modifier = Modifier.padding(bottom = 9.dp)
    )
}

@Composable
fun ReviewImage() {
    Image(
        painter = painterResource(id = R.drawable.test2),
        contentDescription = "test",
        modifier = Modifier
            .size(25.dp)
            .padding(bottom = 7.dp)
    )
}

@Composable
fun ReviewIcon() {
    Icon(
        painter = painterResource(id = R.drawable.vector),
        contentDescription = "test",
        modifier = Modifier
            .size(50.dp)
            .clickable { },
        tint = blue,
    )
}
