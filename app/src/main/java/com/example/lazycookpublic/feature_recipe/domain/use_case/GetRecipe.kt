package com.example.lazycookpublic.feature_recipe.domain.use_case

import com.example.lazycookpublic.core.util.Resource
import com.example.lazycookpublic.feature_recipe.domain.model.recipe.Recipe
import com.example.lazycookpublic.feature_recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(
    private val repository: RecipeRepository
) {
    operator fun invoke(ingredients: String): Flow<Resource<List<Recipe>>> {
        if (ingredients.isBlank()) {
            return flow {  }
        }
        return repository.getRecipes(ingredients)
    }
}