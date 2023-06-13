package com.example.lazycookpublic.feature_recipe.domain.model.ingredient

import com.example.lazycookpublic.feature_recipe.domain.model.ingredient.Ingredient

data class RecipeInformation(
    val recipeInfoTitle: String,
    val recipeInfoImageUrl: String,
    val ingredients: List<Ingredient>,
)