package com.example.ecommerceapplication.UIComponents.Utilites.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.products.R


@Composable
fun RouteImage(){
    Image(
        painter = painterResource(id = R.drawable.logowithbackgroundblue),
        contentDescription = "route-logo",
        modifier = Modifier
            .height(50.dp)
            .size(100.dp)
            .padding(start = 5.dp, top = 10.dp , bottom = 10.dp),
    )
}