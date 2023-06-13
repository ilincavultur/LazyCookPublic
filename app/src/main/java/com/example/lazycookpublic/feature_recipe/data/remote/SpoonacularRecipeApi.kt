package com.example.lazycookpublic.feature_recipe.data.remote


import com.example.lazycookpublic.feature_recipe.data.remote.dto.ingredient.RecipeInfoResponseDto
import com.example.lazycookpublic.feature_recipe.data.remote.dto.recipe.RecipeResponseDto
import com.example.lazycookpublic.feature_recipe.data.remote.dto.step.StepsResponseDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface SpoonacularRecipeApi {

    @Headers(
        "Accept: application/json"
    )
    @GET("recipes/findByIngredients")
    suspend fun getRecipe(@Query("ingredients") ingredientList: String, @Query("apiKey") apiKey: String) : List<RecipeResponseDto>

    @Headers(
        "Accept: application/json"
    )
    @GET("recipes/{id}/information")
    suspend fun getRecipeInformation(@Path("id") id: Int, @Query("apiKey") apiKey: String) : RecipeInfoResponseDto

    @Headers(
        "Accept: application/json"
    )
    @GET("recipes/{id}/analyzedInstructions")
    suspend fun getSteps(@Path("id") id: Int, @Query("apiKey") apiKey: String) : List<StepsResponseDto>
}