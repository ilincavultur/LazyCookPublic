package com.example.lazycookpublic.feature_recipe.domain.use_case

import com.example.lazycookpublic.feature_recipe.data.local.entity.RecipeEntity
import com.example.lazycookpublic.feature_recipe.domain.repository.RecipeRepository

class IsRecipeBookmarkedUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(id: Int): RecipeEntity {
        return repository.getRecipe(id)
    }
}