package com.example.lazycookpublic.feature_recipe.domain.model.recipe

import com.example.lazycookpublic.feature_recipe.data.local.entity.RecipeEntity

data class Recipe(
    val recipeId: Int?, // entity id
    val id: Int, // recipeApiId, not entity id
    val image: String,
    val title: String,
    var isBookmarked: Boolean,
)

fun Recipe.toRecipeEntity() : RecipeEntity {
    return RecipeEntity(
        recipeId = null,
        title = title,
        recipeApiId = id,
        imageUrl = image,
    )
}