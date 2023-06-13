package com.example.lazycookpublic

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lazycookpublic.core.components.bottomnavigation.CustomBottomNavigation
import com.example.lazycookpublic.core.navigation.Navigation
import com.example.lazycookpublic.core.navigation.Screen
import com.example.lazycookpublic.ui.theme.LazyCookPublicTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyCookPublicTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    CustomBottomNavigation(
                        navController = navController,
                        title = when(navBackStackEntry?.destination?.route) {
                            Screen.IngredientScreen.route -> stringResource(id = R.string.ingredient_screen_title)
                            Screen.SavedRecipeScreen.route -> stringResource(id = R.string.saved_recipe_screen_title)
                            else -> {null}
                        },
                        showBottomBar = navBackStackEntry?.destination?.route !in listOf(
                            ""
                        ),
                        showBackArrow = navBackStackEntry?.destination?.route !in listOf(
                            Screen.IngredientScreen.route,
                            Screen.SavedRecipeScreen.route
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        content = {
                            Navigation(
                                navController = navController,
                                startDestination = Screen.IngredientScreen.route,
                                modifier = Modifier.fillMaxSize(),
                            )
                        },
                        backgroundColor = MaterialTheme.colors.background
                    )
                }
            }
        }
    }
}
