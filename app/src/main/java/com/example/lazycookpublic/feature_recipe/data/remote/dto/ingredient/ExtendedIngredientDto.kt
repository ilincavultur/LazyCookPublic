package com.example.lazycookpublic.feature_recipe.data.remote.dto.ingredient

import com.example.lazycookpublic.feature_recipe.domain.model.ingredient.Ingredient

data class ExtendedIngredientDto(
    val aisle: String,
    val amount: Double,
    val consitency: String,
    val id: Int,
    val image: String,
    val measures: MeasuresDto,
    val meta: List<String>,
    val name: String,
    val original: String,
    val originalName: String,
    val unit: String
)

fun ExtendedIngredientDto.toIngredient() : Ingredient {
    return Ingredient(
        amount = amount,
        ingredientApiId = id,
        ingredientImageUrl = image,
        original = original
    )
}