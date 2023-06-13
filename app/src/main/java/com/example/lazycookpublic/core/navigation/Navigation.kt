package com.example.lazycookpublic.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.example.lazycookpublic.feature_ingredient.presentation.IngredientScreen
import com.example.lazycookpublic.feature_recipe.presentation.SearchRecipeScreen
import com.example.lazycookpublic.feature_recipe.presentation.recipe_details.RecipeDetailsScreen
import com.example.lazycookpublic.feature_recipe.presentation.saved_recipes.SavedRecipeScreen

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun Navigation(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier.fillMaxSize()
    ) {
        composable(Screen.IngredientScreen.route) {
            IngredientScreen(navController = navController, onNavigateToSearchRecipe = {
                navController.navigate(Screen.SearchRecipeScreen.withStringArgs(it), navOptions {
                    navArgument("ingredientsString") {
                        type = NavType.StringType
                        nullable = true
                    }
                })
            })
        }
        composable(Screen.SavedRecipeScreen.route) {
            SavedRecipeScreen(navController = navController, onNavigateToRecipeDetails = { entityId, apiId ->
                navController.navigate(Screen.RecipeDetailsScreen.withArgs(entityId, apiId), navOptions {
                    navArgument("recipeEntityId") {
                        type = NavType.IntType
                        nullable = false
                    }
                    navArgument("recipeApiId") {
                        type = NavType.IntType
                        nullable = false
                    }
                })
            })
        }
        composable(
            Screen.SearchRecipeScreen.route + "/{ingredientsString}",
            arguments = listOf(navArgument("ingredientsString") {
                type = NavType.StringType
                nullable = true
            })
        ) {
            SearchRecipeScreen(navController = navController, onNavigateToRecipeDetails = { entityId, apiId ->
                navController.navigate(Screen.RecipeDetailsScreen.withArgs(entityId, apiId), navOptions {
                    navArgument("recipeEntityId") {
                        type = NavType.IntType
                        nullable = false
                        defaultValue = -1
                    }
                    navArgument("recipeApiId") {
                        type = NavType.IntType
                        nullable = false
                    }
                })
            })
        }

        composable(
            Screen.RecipeDetailsScreen.route + "/{recipeEntityId}/{recipeApiId}",
            arguments = listOf(
                navArgument("recipeEntityId") {
                    type = NavType.IntType
                    nullable = false
                    defaultValue = -1
                },
                navArgument("recipeApiId") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) {
            RecipeDetailsScreen(navController = navController)
        }
    }

}