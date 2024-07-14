package com.example.products.Search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.products.R
import com.example.products.ui.theme.blue
import com.example.products.ui.theme.searchBarColor

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    searchText: String,
    modifier: Modifier = Modifier,
    onSearchTextChanged: (String) -> Unit,
    onSearchClicked: () -> Unit,
    onClearClicked: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    // Remember the state of the keyboard visibility
    val isKeyboardVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = searchText,
        onValueChange = {
            onSearchTextChanged(it)
            isKeyboardVisible.value = it.isNotEmpty() // Update keyboard visibility state
        },
        placeholder = {
            Text(
                "What do you search for?",
                color = Color.LightGray,
                fontSize = 14.sp,
                maxLines = 1,
                textAlign = TextAlign.Start
            )
        },
        modifier = modifier
            .padding(horizontal = 1.dp)
            .fillMaxWidth(),
        textStyle = TextStyle(
            color = blue, // Set text color to blue
            fontSize = 16.sp // Set text size to 16sp
        ),
        leadingIcon = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.icon__search_),
                    contentDescription = "Search Icon",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = onClearClicked, enabled = searchText.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear Search",
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = blue,
            unfocusedBorderColor = blue,
            focusedLabelColor = searchBarColor,
            unfocusedLabelColor = searchBarColor,
            cursorColor = blue
        ),
        shape = RoundedCornerShape(25.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            focusManager.clearFocus() // Clear focus when keyboard hides
        })
    )
}
