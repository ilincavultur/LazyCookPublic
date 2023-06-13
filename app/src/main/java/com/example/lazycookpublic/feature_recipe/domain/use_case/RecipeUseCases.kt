package com.example.lazycookpublic.feature_recipe.domain.use_case

data class RecipeUseCases(
    val getRecipe: GetRecipe,
    val getSavedRecipes: GetSavedRecipes,
    val getSavedRecipe: GetSavedRecipe,
    val saveRecipe: SaveRecipeUseCase,
    val deleteRecipe: DeleteRecipeUseCase,
    val isRecipeBookmarked: IsRecipeBookmarkedUseCase,
    val getRecipeInfo: GetRecipeInfo,
    val getRecipeSteps: GetRecipeSteps,
)
