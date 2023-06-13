package com.example.lazycookpublic.feature_recipe.data.remote.dto.recipe

import com.example.lazycookpublic.feature_recipe.data.local.entity.RecipeEntity
import com.example.lazycookpublic.feature_recipe.domain.model.recipe.Recipe

data class RecipeResponseDto(
    val id: Int,
    val image: String,
    val imageType: String,
    val likes: Int,
    val missedIngredientCount: Int,
    val missedIngredients: List<RecipeIngredientDto>,
    val title: String,
    val unusedIngredients: List<RecipeIngredientDto>,
    val usedIngredientCount: Int,
    val usedIngredients: List<RecipeIngredientDto>
)

fun RecipeResponseDto.toRecipe() : Recipe {
    return Recipe(
        recipeId = null, id, image, title, isBookmarked = false
    )
}

fun RecipeResponseDto.toRecipeEntity() : RecipeEntity {
    return RecipeEntity(
        title = title,
        recipeApiId = id,
        imageUrl = image
    )
}