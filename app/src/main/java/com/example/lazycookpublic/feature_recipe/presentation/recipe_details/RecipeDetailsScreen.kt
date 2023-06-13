package com.example.lazycookpublic.feature_recipe.presentation.recipe_details

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray

import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lazycookpublic.core.components.cards.ImageWithText
import com.example.lazycookpublic.core.components.scaffold.BottomScaffold
import kotlinx.coroutines.flow.collectLatest

@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("UnusedTransitionTargetStateParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeDetailsScreen(
    navController: NavController,
    viewModel: RecipeDetailsViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val bottomScaffoldState = rememberBottomSheetScaffoldState()

    val transitionState = remember {
        MutableTransitionState(false).apply {
            targetState = !bottomScaffoldState.bottomSheetState.isExpanded
        }
    }
    val transition = updateTransition(transitionState, label = "")

    val cardBgColor by transition.animateColor(
        label = "cardBgColorTransition",
        transitionSpec = { tween(durationMillis = 1500) },
        targetValueByState = {
            if (bottomScaffoldState.bottomSheetState.isExpanded) com.example.lazycookpublic.ui.theme.Green else  MaterialTheme.colors.background
        }
    )

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is RecipeDetailsUiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    BottomSheetScaffold(
        scaffoldState = bottomScaffoldState,
        sheetShape = RoundedCornerShape(20.dp),
        sheetElevation = 70.dp,
        drawerElevation = 70.dp,
        sheetPeekHeight = 100.dp,
        sheetContent = {
            BottomScaffold(state.steps, cardBgColor)//Create a sheet Composable
        }
    ) {
        ImageWithText(imageUrl = state.imageUrl ?: "", text = state.name ?: "")

        Column (
            modifier = Modifier.padding(20.dp)
                ) {
            Text(
                text = "Ingredients",
                color = DarkGray,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    shadow = Shadow(
                        color = Color.DarkGray
                    )
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
            LazyColumn (

            ) {
                itemsIndexed(state.ingredients) { idx, ingredient ->
                    Text(
                        text = ingredient.original,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                }
            }
        }
    }
}