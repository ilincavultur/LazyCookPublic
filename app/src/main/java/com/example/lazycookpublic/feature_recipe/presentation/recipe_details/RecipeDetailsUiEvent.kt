package com.example.lazycookpublic.feature_recipe.presentation.recipe_details


sealed class RecipeDetailsUiEvent {
    data class ShowSnackbar(val message: String): RecipeDetailsUiEvent()
}
