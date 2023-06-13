package com.example.lazycookpublic.feature_recipe.data.remote.dto.ingredient

data class ProductMatcheDto(
    val averageRating: Double,
    val description: String,
    val id: Int,
    val imageUrl: String,
    val link: String,
    val price: String,
    val ratingCount: Double,
    val score: Double,
    val title: String
)