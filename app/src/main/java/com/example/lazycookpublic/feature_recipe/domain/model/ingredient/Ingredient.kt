package com.example.lazycookpublic.feature_recipe.domain.model.ingredient

import com.example.lazycookpublic.feature_recipe.data.local.entity.RecipeIngredientEntity

data class Ingredient(
    val amount: Double,
    val ingredientApiId: Int,
    val ingredientImageUrl: String? = "",
    val original: String, // description
)

fun Ingredient.toRecipeIngredientEntity() : RecipeIngredientEntity {
    return RecipeIngredientEntity(
        amount = amount,
        recipeIngredientApiId = ingredientApiId,
        ingredientImageUrl = ingredientImageUrl ?: "",
        original = original
    )
}
