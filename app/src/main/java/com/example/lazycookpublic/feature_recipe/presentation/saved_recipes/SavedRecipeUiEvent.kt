package com.example.lazycookpublic.feature_recipe.presentation.saved_recipes

sealed class SavedRecipeUiEvent {
    data class ShowSnackbar(val message: String): SavedRecipeUiEvent()
}