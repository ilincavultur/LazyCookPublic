package com.example.lazycookpublic.feature_ingredient.presentation

import com.example.lazycookpublic.feature_ingredient.domain.model.Ingredient

data class IngredientState(
    val ingredientItems: List<Ingredient> = emptyList(),
    val selectedIngredientItems: List<Ingredient> = emptyList<Ingredient>(),
    val isLoading: Boolean = false
)
