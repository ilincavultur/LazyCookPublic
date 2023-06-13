package com.example.lazycookpublic.feature_ingredient.domain.use_case

import com.example.lazycookpublic.core.util.Resource
import com.example.lazycookpublic.feature_ingredient.domain.model.Ingredient
import com.example.lazycookpublic.feature_ingredient.domain.repository.IngredientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetIngredient(
    private val repository: IngredientRepository
) {
    operator fun invoke(ingredient: String): Flow<Resource<List<Ingredient>>> {
        if (ingredient.isBlank()) {
            return flow {  }
        }
        return repository.getIngredients(ingredient)
    }
}