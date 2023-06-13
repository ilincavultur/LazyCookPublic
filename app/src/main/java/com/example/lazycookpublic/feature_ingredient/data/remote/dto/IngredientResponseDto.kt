package com.example.lazycookpublic.feature_ingredient.data.remote.dto

import com.example.lazycookpublic.feature_ingredient.domain.model.IngredientList

data class IngredientResponseDto(
    val number: Int,
    val offset: Int,
    val results: List<IngredientDto>,
    val totalResults: Int
)

fun IngredientResponseDto.toIngredientList() : IngredientList {
    return IngredientList(
        ingredients = results.map {
            it.toIngredient()
        }
    )
}
