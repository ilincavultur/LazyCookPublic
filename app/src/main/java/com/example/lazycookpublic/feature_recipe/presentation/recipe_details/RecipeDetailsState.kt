package com.example.lazycookpublic.feature_recipe.presentation.recipe_details

import com.example.lazycookpublic.feature_recipe.domain.model.ingredient.Ingredient
import com.example.lazycookpublic.feature_recipe.domain.model.step.StepInfo

data class RecipeDetailsState(
    val name: String = "",
    val imageUrl: String = "",
    val ingredients: List<Ingredient> = emptyList(),
    val isLoading: Boolean = false,
    val isBottomSheetCollapsed: Boolean = false,
    val steps: List<StepInfo> = emptyList(),
) {
}