package com.example.lazycookpublic.feature_recipe.data.remote.dto.ingredient

import android.content.ContentValues.TAG
import android.util.Log
import com.example.lazycookpublic.feature_recipe.domain.model.ingredient.RecipeInformation

data class RecipeInfoResponseDto(
    val analyzedInstructions: List<Any>,
    val cheap: Boolean,
    val creditsText: String,
    val cuisines: List<Any>,
    val dairyFree: Boolean,
    val diets: List<Any>,
    val dishTypes: List<String>,
    val extendedIngredients: List<ExtendedIngredientDto>,
    val gaps: String,
    val glutenFree: Boolean,
    val healthScore: Double,
    val id: Int,
    val image: String,
    val imageType: String,
    val instructions: String,
    val ketogenic: Boolean,
    val license: String,
    val lowFodmap: Boolean,
    val occasions: List<Any>,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularScore: Double,
    val spoonacularSourceUrl: String,
    val summary: String,
    val sustainable: Boolean,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val weightWatcherSmartPoints: Int,
    val whole30: Boolean,
    val winePairing: WinePairingDto
)

fun RecipeInfoResponseDto.toRecipeInformation() : RecipeInformation {
    return RecipeInformation(
        recipeInfoTitle = title,
        recipeInfoImageUrl = image,
        ingredients = extendedIngredients.map {
            Log.d(TAG, "toRecipeInformation: " + it.name)
            it.toIngredient()
        }
    )
}