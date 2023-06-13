package com.example.lazycookpublic.feature_recipe.presentation

import com.example.lazycookpublic.feature_recipe.domain.model.recipe.Recipe


sealed class RecipeEvent{
    data class OnRowClick(val recipe: Recipe) : RecipeEvent()
    data class OnBookmarkClick(val recipe: Recipe) : RecipeEvent()
}