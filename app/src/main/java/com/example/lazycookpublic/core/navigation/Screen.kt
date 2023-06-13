package com.example.lazycookpublic.core.navigation

import androidx.annotation.StringRes
import com.example.lazycookpublic.R

sealed class Screen(
    val route: String, @StringRes val resourceId: Int
) {
    object IngredientScreen : Screen("ingredient_screen", R.string.ingredient_screen)
    object SearchRecipeScreen : Screen("search_recipe_screen", R.string.search_recipe_screen)
    object SavedRecipeScreen : Screen("saved_recipe_screen", R.string.saved_recipe_screen)
    object RecipeDetailsScreen : Screen("recipe_details_screen", R.string.recipe_details_screen)

    fun withArgs(vararg args: Int?): String {
        return buildString {
            append(route)
            args.forEach{
                if (it != null) {
                    append("/$it")
                }
            }
        }
    }

    fun withStringArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach{
                append("/$it")
            }
        }
    }
}