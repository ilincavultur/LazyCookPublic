package com.example.lazycookpublic.feature_ingredient.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.lazycookpublic.core.components.cards.IngredientCard
import com.example.lazycookpublic.core.components.cards.SelectedIngredientCard
import com.example.lazycookpublic.core.util.Constants
import com.example.lazycookpublic.feature_ingredient.domain.model.Ingredient
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun IngredientScreen(
    navController: NavHostController,
    viewModel: IngredientViewModel = hiltViewModel(),
    onNavigateToSearchRecipe: (String) -> Unit,
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is IngredientUiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    if (state.isLoading) {
        CircularProgressIndicator()
    }

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {

        OutlinedTextField(
            value = viewModel.searchQuery.value,
            onValueChange = viewModel::onSearch,
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            placeholder = {
                Text(
                    text = "Search...",
                    style = TextStyle(
                        color = Color.Gray,
                    )
                )
            },
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .size(400.dp)
                .padding(25.dp)
        ) {
            itemsIndexed(state.ingredientItems) { idx, ingredient ->
                if (idx != 0) {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.onEvent(IngredientEvent.OnRowClick(ingredient.id))
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IngredientCard(ingredient = ingredient, isInList = viewModel.ingredientIsInList(ingredient.id))
                }
                Spacer(modifier = Modifier.size(10.dp))
                Divider()
            }
        }
        if (state.selectedIngredientItems.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 10.dp
            ) {
                Column {

                    Text(
                        text = "Selected Ingredients",
                        modifier = Modifier
                            .padding(14.dp)
                    )

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        itemsIndexed(state.selectedIngredientItems, key = { _, item: Ingredient ->
                            item.hashCode()
                        }) { idx, ingredient ->
                            Spacer(modifier = Modifier.size(5.dp))
                            Log.d(TAG, "IngredientScreen: selected item: " + ingredient.name)
                            SelectedIngredientCard(ingredient.name, Constants.imageUrlBase + ingredient.image)
                            Spacer(modifier = Modifier.size(5.dp))
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                viewModel.onEvent(IngredientEvent.OnCancelClick)
                            }) {
                            Text(text = "Cancel")
                        }

                        Button(
                            onClick = { onNavigateToSearchRecipe(viewModel.getIngredientsString(state.selectedIngredientItems)) }
                        ) {
                            Text(text = "Recipe")
                        }
                    }
                }
            }
        }
    }
}