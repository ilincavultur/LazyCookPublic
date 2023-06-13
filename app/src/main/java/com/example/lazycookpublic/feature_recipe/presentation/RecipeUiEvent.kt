package com.example.lazycookpublic.feature_recipe.presentation

sealed class RecipeUiEvent{
    data class ShowSnackbar(val message: String): RecipeUiEvent()
}