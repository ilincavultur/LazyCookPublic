package com.example.lazycookpublic.feature_recipe.presentation.saved_recipes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lazycookpublic.core.components.cards.DraggableCard
import com.example.lazycookpublic.core.util.Constants.CARD_OFFSET
import kotlinx.coroutines.flow.collectLatest


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SavedRecipeScreen(
    navController: NavController,
    viewModel: SavedRecipeViewModel = hiltViewModel(),
    onNavigateToRecipeDetails: (Int, Int) -> Unit,
) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is SavedRecipeUiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            itemsIndexed(state.savedRecipeItems) { idx, recipe ->
                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator()
                    }
                    Column {
                        Box(
                            Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colors.secondary,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .clickable {
                                        viewModel.onEvent(SavedRecipeEvent.RemoveItem(recipe.id))
                                    }
                                    .align(Alignment.CenterStart)
                            )

                            DraggableCard(
                                recipe = recipe,
                                isRevealed = state.revealedRecipeItemIds.contains(recipe.id),
                                cardOffset = CARD_OFFSET,
                                onExpand = { viewModel.onEvent(SavedRecipeEvent.ExpandItem(recipe.id)) },
                                onCollapse = { viewModel.onEvent(SavedRecipeEvent.CollapseItem(recipe.id)) },
                                onNavigateToRecipeDetails = onNavigateToRecipeDetails
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
                //Divider()
            }
        }
    }

}