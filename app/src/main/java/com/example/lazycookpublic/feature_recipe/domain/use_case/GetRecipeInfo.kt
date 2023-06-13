package com.example.lazycookpublic.feature_recipe.domain.use_case

import android.content.ContentValues.TAG
import android.util.Log
import com.example.lazycookpublic.core.util.Resource
import com.example.lazycookpublic.feature_recipe.domain.model.ingredient.RecipeInformation
import com.example.lazycookpublic.feature_recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetRecipeInfo(
    private val repository: RecipeRepository
) {
    operator fun invoke(id: Int): Flow<Resource<RecipeInformation>> {
        Log.d(TAG, "invoke: id" + id)
        return repository.getRecipeInfo(id)
    }
}