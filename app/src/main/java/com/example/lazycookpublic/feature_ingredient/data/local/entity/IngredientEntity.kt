package com.example.lazycookpublic.feature_ingredient.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lazycookpublic.feature_ingredient.domain.model.Ingredient

@Entity(tableName = "ingredient_table")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val ingredientId: Int? = null,
    val id: Int,
    val image: String,
    val name: String
)

fun IngredientEntity.toIngredient() : Ingredient {
    return Ingredient(
        id = id,
        image = image,
        name = name
    )
}