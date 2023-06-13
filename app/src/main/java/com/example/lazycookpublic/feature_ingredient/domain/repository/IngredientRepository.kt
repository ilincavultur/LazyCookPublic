package com.example.lazycookpublic.feature_ingredient.domain.repository

import com.example.lazycookpublic.core.util.Resource
import com.example.lazycookpublic.feature_ingredient.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientRepository {
    fun getIngredients(ingredient: String): Flow<Resource<List<Ingredient>>>
}