package com.example.lazycookpublic.feature_recipe.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.lazycookpublic.core.components.cards.RecipeCard
import com.example.lazycookpublic.feature_recipe.presentation.RecipeEvent
import com.example.lazycookpublic.feature_recipe.presentation.RecipeUiEvent
import com.example.lazycookpublic.feature_recipe.presentation.RecipeViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchRecipeScreen(
    navController: NavHostController,
    viewModel: RecipeViewModel = hiltViewModel(),
    onNavigateToRecipeDetails: (Int, Int) -> Unit
) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is RecipeUiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Column {
        if (state.isLoading) {
            CircularProgressIndicator()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            itemsIndexed(state.recipeItems) { idx, recipe ->
                Spacer(modifier = Modifier.size(10.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = remember {
                        RoundedCornerShape(10.dp)
                    },
                    elevation = 2.dp,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            val isBookmarked = remember {
                                mutableStateOf(recipe.isBookmarked)
                            }
                            Icon(
                                imageVector = if (!isBookmarked.value) Icons.Default.BookmarkBorder else Icons.Default.BookmarkAdded,
                                contentDescription = "Selected",
                                tint = MaterialTheme.colors.secondary,
                                modifier = Modifier.clickable {
                                    recipe.isBookmarked = !recipe.isBookmarked
                                    isBookmarked.value = recipe.isBookmarked
                                    Log.d(
                                        TAG,
                                        "SearchRecipeScreen: isbookmarked " + recipe.isBookmarked
                                    )
                                    viewModel.onEvent(RecipeEvent.OnBookmarkClick(recipe))
                                }
                            )
                        }

                        Spacer(modifier = Modifier.size(5.dp))

                        Column {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onNavigateToRecipeDetails(recipe.recipeId ?: -1, recipe.id)
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                RecipeCard(recipe = recipe)
                            }


                        }
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
                //Divider()
            }
        }
    }

}