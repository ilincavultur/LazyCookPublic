package com.example.lazycookpublic.feature_recipe.data.remote.dto.ingredient

import com.example.lazycookpublic.feature_recipe.data.remote.dto.ingredient.ProductMatcheDto

data class WinePairingDto(
    val pairedWines: List<String>,
    val pairingText: String,
    val productMatches: List<ProductMatcheDto>
)