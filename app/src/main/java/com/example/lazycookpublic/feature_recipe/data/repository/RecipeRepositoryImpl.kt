package com.example.lazycookpublic.feature_recipe.data.repository

import android.content.ContentValues.TAG
import android.util.Log

import com.example.lazycookpublic.core.util.Resource
import com.example.lazycookpublic.feature_recipe.data.local.RecipeDao


import com.example.lazycookpublic.feature_recipe.data.remote.SpoonacularRecipeApi
import com.example.lazycookpublic.feature_recipe.data.remote.dto.ingredient.toRecipeInformation
import com.example.lazycookpublic.feature_recipe.data.remote.dto.recipe.toRecipe
import com.example.lazycookpublic.feature_recipe.data.remote.dto.step.toStepInfo
import com.example.lazycookpublic.feature_recipe.domain.model.ingredient.RecipeInformation
import com.example.lazycookpublic.feature_recipe.domain.model.recipe.Recipe
import com.example.lazycookpublic.feature_recipe.domain.model.recipe.toRecipeEntity
import com.example.lazycookpublic.feature_recipe.domain.model.step.StepInfo
import com.example.lazycookpublic.feature_recipe.domain.repository.RecipeRepository
import com.example.lazycookpublic.BuildConfig
import com.example.lazycookpublic.feature_recipe.data.local.entity.RecipeEntity
import com.example.lazycookpublic.feature_recipe.data.local.entity.toRecipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import retrofit2.HttpException
import java.io.IOException


class RecipeRepositoryImpl(
    private val api: SpoonacularRecipeApi,
    private val dao: RecipeDao
) : RecipeRepository {
    override fun getRecipes(ingredients: String): Flow<Resource<List<Recipe>>> = flow { // on search for recipe with ingredients
        emit(Resource.Loading())

        try {
            val remoteRecipes = api.getRecipe(ingredients, BuildConfig.SPOONACULAR_INGREDIENT_API_KEY)

            emit(Resource.Success(remoteRecipes.map {
                it.toRecipe()
            }))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Ooops something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Check internet connection"))
        }


    }

    override fun getSavedRecipes(): Flow<Resource<List<Recipe>>> = flow { // for the bookmarked recipes screen
        emit(Resource.Loading())

        try {
            val savedRecipes = dao.getAllRecipes()

            Log.d(TAG, "getSavedRecipes: ")
            emit(Resource.Success(savedRecipes.map {
                it.toRecipe()
            }))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Ooops something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Check internet connection"))
        }
    }

    override fun getRecipeInfo(id: Int): Flow<Resource<RecipeInformation>> = flow {
        emit(Resource.Loading())
        Log.d(TAG, "getRecipeInfo: before " + id.toString())

//        val recipe = dao.getRecipe(id)
//        val recipeIngredients = dao.getRecipeIngredients(recipe.recipeId!!).map {
//            it.toIngredient()
//        }
//        val recipeInfo = RecipeInformation(recipe.title, recipe.imageUrl, recipeIngredients)
//        emit(Resource.Loading(data = recipeInfo))

        try {
            val recipeInformation = api.getRecipeInformation(id, BuildConfig.SPOONACULAR_INGREDIENT_API_KEY)

//            Log.d(TAG, "getRecipeInfo: " + recipeInformation.title)
//
//            val ingredients = recipeInformation.toRecipeInformation().ingredients.map {
//                it.toRecipeIngredientEntity()
//            }
//
//            ingredients.forEach {
//                it.recipeIdForeign = recipe.recipeId
//            }
//            dao.deleteRecipeIngredients(ingredients)
//            dao.insertRecipeIngredients(ingredients)
//

            emit(Resource.Success(recipeInformation.toRecipeInformation()))
        } catch (e: HttpException) {
            Log.d(TAG, "Ooops something went wrong " + e.message + e.code() + e.response())

            emit(Resource.Error(message = "Ooops something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Check internet connection"))
        }

//        val newRecipeInfo = dao.getRecipe(id)
//        val newRecipeIngredients = dao.getRecipeIngredients(recipe.recipeId!!).map {
//            it.toIngredient()
//        }
//        val newRecipeInfos = RecipeInformation(newRecipeInfo.title, newRecipeInfo.imageUrl, newRecipeIngredients)
//        emit(Resource.Success(newRecipeInfos))
    }

    override fun getRecipeSteps(id: Int): Flow<Resource<List<StepInfo>>> = flow {
        emit(Resource.Loading())

//        val recipe = dao.getRecipe(id)
//        val recipeSteps = emptyList<Step>().toMutableList()
//
//        dao.getRecipeSteps(recipe.recipeId!!).forEach {
//            recipeSteps += it.toStep()
//        }
//
//        val stepInfo = listOf(StepInfo(recipeSteps))
//
//        emit(Resource.Loading(data = stepInfo))

        try {
            val recipeSteps = api.getSteps(id, BuildConfig.SPOONACULAR_INGREDIENT_API_KEY)

//            val steps = recipeSteps.map {
//                it.toStepInfo().steps
//            }
//
//            val stepEntities = emptyList<StepEntity>().toMutableList()
//
//            steps.forEach { list ->
//                list.forEach {
//                    stepEntities += it.toStepEntity()
//                }
//            }
//
//            stepEntities.forEach {
//                it.recipeStepIdForeign = recipe.recipeId
//            }
//
//            dao.deleteRecipeSteps(stepEntities)
//            dao.insertRecipeSteps(stepEntities)

            emit(Resource.Success(recipeSteps.map {
                it.toStepInfo()
            }))
        } catch (e: HttpException) {
            Log.d(TAG, "Ooops something went wrong " + e.message + e.code() + e.response())

            emit(Resource.Error(message = "Ooops something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Check internet connection"))
        }

//        val newRecipeSteps = emptyList<Step>().toMutableList()
//
//        dao.getRecipeSteps(recipe.recipeId!!).forEach {
//            newRecipeSteps += it.toStep()
//        }
//
//        val newStepInfo = listOf(StepInfo(newRecipeSteps))
//
//        emit(Resource.Success(newStepInfo))
    }

    override suspend fun getRecipe(id: Int): RecipeEntity { // to check if recipe is bookmarked
        return dao.getRecipe(id)
    }

    override fun getRecipeFlow(id: Int): Flow<Resource<Recipe>> = flow { // for the bookmarked recipe details screen
        emit(Resource.Loading())
        try {
            val savedRecipe = dao.getRecipe(id)

            emit(Resource.Success(savedRecipe.toRecipe()))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Ooops something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Check internet connection"))
        }
    }

    override suspend fun saveRecipe(recipe: Recipe) { // to save recipe (bookmark)
        dao.saveRecipe(recipe = recipe.toRecipeEntity())
    }

    override suspend fun deleteRecipe(id: Int) { // to un save recipe (un bookmark)
        dao.deleteRecipe(id)
    }
}