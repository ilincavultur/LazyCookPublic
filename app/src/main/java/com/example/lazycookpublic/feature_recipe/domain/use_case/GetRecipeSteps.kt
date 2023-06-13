package com.example.lazycookpublic.feature_recipe.domain.use_case

import com.example.lazycookpublic.core.util.Resource
import com.example.lazycookpublic.feature_recipe.domain.model.step.StepInfo
import com.example.lazycookpublic.feature_recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetRecipeSteps(
    private val repository: RecipeRepository
) {

    operator fun invoke(id: Int): Flow<Resource<List<StepInfo>>> {
        return repository.getRecipeSteps(id)
    }

}