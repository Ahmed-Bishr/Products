package com.example.myshop.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.products.R


@Composable
fun CartIcon(modifier: Modifier){
    Image(
        painter = painterResource(id = R.drawable.icon_cart),
        contentDescription = "Cart Icon", modifier = Modifier.padding(end = 10.dp).size(40.dp)
    )
}

