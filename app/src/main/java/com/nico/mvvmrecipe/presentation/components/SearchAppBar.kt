package com.nico.mvvmrecipe.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nico.mvvmrecipe.presentation.ui.recipe_list.FoodCategory
import com.nico.mvvmrecipe.presentation.ui.recipe_list.getAllFoodCategories
import kotlinx.coroutines.launch

@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    scrollPosition: Int,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    onChangeCategoryScrollPosition: (Int) -> Unit
) {
    val keyboardController = LocalFocusManager.current

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.White,
        elevation = 8.dp
    ) {

        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(8.dp),
                    value = query,
                    onValueChange = { newValue ->
                        onQueryChanged(newValue)
                    },
                    label = {
                        Text(text = "Search")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    leadingIcon = {
                        Icon(Icons.Filled.Search, "")
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onExecuteSearch()
                            keyboardController.clearFocus()
                        }
                    ),
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    )
                )
            }

            val lazyListState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()
            val categories = getAllFoodCategories()

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 8.dp),
                state = lazyListState
            ) {

                coroutineScope.launch {
                    lazyListState.scrollToItem(scrollPosition)
                }

                items(items = categories, itemContent = { category ->
                    FoodCategoryChips(
                        category = category.value,
                        isSelected = selectedCategory == category,
                        onSelectedCategoryChanged = {
                            onSelectedCategoryChanged(it)
                            onChangeCategoryScrollPosition(lazyListState.firstVisibleItemScrollOffset)
                        },
                        onExecuteSearch = { onExecuteSearch() }
                    )
                })

            }

        }

    }
}