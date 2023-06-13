package com.example.lazycookpublic.feature_recipe.presentation.saved_recipes

import com.example.lazycookpublic.feature_recipe.domain.model.recipe.Recipe


data class SavedRecipeState(
    val savedRecipeItems: List<Recipe> = emptyList(),
    val revealedRecipeItemIds: List<Int> = emptyList(),
    val isLoading: Boolean = false
)
