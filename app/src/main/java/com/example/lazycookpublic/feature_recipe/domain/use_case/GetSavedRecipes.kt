package com.example.lazycookpublic.feature_recipe.domain.use_case

import com.example.lazycookpublic.core.util.Resource
import com.example.lazycookpublic.feature_recipe.domain.model.recipe.Recipe
import com.example.lazycookpublic.feature_recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetSavedRecipes (
    private val repository: RecipeRepository
) {
    operator fun invoke(): Flow<Resource<List<Recipe>>> {
        return repository.getSavedRecipes()
    }
}