package com.example.lazycookpublic.core.components.bottomnavigation


import androidx.compose.foundation.layout.*

import androidx.compose.ui.Alignment
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.lazycookpublic.core.components.topbar.CustomTopBar
import com.example.lazycookpublic.core.navigation.Screen

@Composable
fun CustomBottomNavigation(
    navController: NavController,
    modifier: Modifier = Modifier,
    title: String?,
    showBottomBar: Boolean = true,
    showBackArrow: Boolean = false,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = Color.Gray,
    content: @Composable () -> Unit,
    items: List<BottomNavigationItem> = listOf(
        BottomNavigationItem(
            route = Screen.IngredientScreen.route,
            icon = Icons.Default.Search,
            contentDescription = "Ingredients"
        ),
        BottomNavigationItem(
            route = Screen.SavedRecipeScreen.route,
            icon = Icons.Default.BookmarkBorder,
            contentDescription = "Recipes"
        ),
    ),
    onFabClick: () -> Unit = {}
): Unit {
    Scaffold(
        topBar = {
             CustomTopBar(
                 title = title
             )
        },
        bottomBar = {
            if (showBottomBar && !showBackArrow) {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = backgroundColor,
                    contentColor = contentColor
                ) {
                    BottomNavigation(
                        backgroundColor = MaterialTheme.colors.background,
                    ) {
                        items.forEachIndexed { index, item ->

                                BottomNavigationItem(
                                    icon = item.icon,
                                    unselectedContentColor = Color.Gray,
                                    selectedContentColor = MaterialTheme.colors.secondary,
                                    selected = item.route == navController.currentDestination?.route,
                                    onClick = {
                                        if (navController.currentDestination?.route != item.route) {
                                            navController.navigate(item.route)
                                        }
                                    },
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .size(24.dp)
                                        .weight(0.5f)

                                )

                        }
                    }
                }
            } else if (showBottomBar && showBackArrow) {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = backgroundColor,
                    contentColor = contentColor,

                ) {
                    BottomNavigation(
                        backgroundColor = MaterialTheme.colors.background,

                    ) {

                            BottomNavigationItem(
                                route = Screen.IngredientScreen.route,
                                icon = Icons.Default.ArrowBack,
                                contentDescription = "Home"
                            ).apply {
                                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                                    BottomNavigationItem(
                                        icon = this.icon,
                                        unselectedContentColor = Color.Gray,
                                        selectedContentColor = MaterialTheme.colors.secondary,
                                        selected = this.route == navController.currentDestination?.route,
                                        onClick = {
                                            navController.popBackStack()
                                        },
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .size(24.dp)
                                    )
                                }
                                Spacer(Modifier.weight(2.5f, true))
                            }
                    }
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        modifier = modifier
    ) {
         innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                content()
            }
    }


}