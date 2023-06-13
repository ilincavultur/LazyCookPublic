package com.example.lazycookpublic.feature_recipe.domain.use_case


import com.example.lazycookpublic.feature_recipe.domain.model.recipe.Recipe
import com.example.lazycookpublic.feature_recipe.domain.repository.RecipeRepository

class SaveRecipeUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipe: Recipe) {
        repository.saveRecipe(recipe)
    }
}