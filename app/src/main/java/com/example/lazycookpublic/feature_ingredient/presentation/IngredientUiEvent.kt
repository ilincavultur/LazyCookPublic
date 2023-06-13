package com.example.lazycookpublic.feature_ingredient.presentation

sealed class IngredientUiEvent{
    data class ShowSnackbar(val message: String): IngredientUiEvent()
}
