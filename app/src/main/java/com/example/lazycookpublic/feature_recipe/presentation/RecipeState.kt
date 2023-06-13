package com.example.lazycookpublic.feature_recipe.presentation

import com.example.lazycookpublic.feature_recipe.domain.model.recipe.Recipe


data class RecipeState(
    val recipeItems: List<Recipe> = emptyList(),
    val isLoading: Boolean = false
)