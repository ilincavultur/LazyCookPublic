package com.example.lazycookpublic.feature_recipe.domain.repository

import com.example.lazycookpublic.core.util.Resource
import com.example.lazycookpublic.feature_recipe.data.local.entity.RecipeEntity
import com.example.lazycookpublic.feature_recipe.domain.model.ingredient.RecipeInformation
import com.example.lazycookpublic.feature_recipe.domain.model.recipe.Recipe
import com.example.lazycookpublic.feature_recipe.domain.model.step.StepInfo
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipes(ingredients: String) : Flow<Resource<List<Recipe>>>

    fun getSavedRecipes() : Flow<Resource<List<Recipe>>>

    fun getRecipeInfo(id: Int) : Flow<Resource<RecipeInformation>>

    fun getRecipeSteps(id: Int) : Flow<Resource<List<StepInfo>>>

    suspend fun getRecipe(id: Int) : RecipeEntity

    fun getRecipeFlow(id: Int) : Flow<Resource<Recipe>>

    suspend fun saveRecipe(recipe: Recipe)

    suspend fun deleteRecipe(id: Int)
}