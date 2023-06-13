package com.example.lazycookpublic.feature_ingredient.domain.model

data class Ingredient(
    val id: Int,
    val image: String,
    val name: String,
    var isSelected: Boolean = false
)
