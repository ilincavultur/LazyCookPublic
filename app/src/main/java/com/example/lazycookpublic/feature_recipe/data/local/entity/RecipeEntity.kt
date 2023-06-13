package com.example.lazycookpublic.feature_recipe.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lazycookpublic.feature_recipe.domain.model.recipe.Recipe


@Entity(tableName = "recipe_table")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val recipeId: Int? = null,
    val title: String,
    val recipeApiId: Int,
    val imageUrl: String
)

fun RecipeEntity.toRecipe() : Recipe {
    return Recipe(
        recipeId = recipeId,
        id = recipeApiId,
        image = imageUrl,
        title = title,
        isBookmarked = true
    )
}