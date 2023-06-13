package com.example.lazycookpublic.feature_ingredient.data.remote.dto

import com.example.lazycookpublic.feature_ingredient.data.local.entity.IngredientEntity
import com.example.lazycookpublic.feature_ingredient.domain.model.Ingredient

data class IngredientDto(
    val id: Int,
    val image: String,
    val name: String
)

fun IngredientDto.toIngredient() : Ingredient {
    return Ingredient(
        id = id,
        image = image,
        name = name
    )
}

fun IngredientDto.toIngredientEntity() : IngredientEntity {
    return IngredientEntity(
        id = id,
        image = image,
        name = name
    )
}