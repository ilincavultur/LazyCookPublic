package com.example.lazycookpublic.feature_recipe.presentation.saved_recipes

sealed class SavedRecipeEvent {
    data class CollapseItem(val id: Int): SavedRecipeEvent()
    data class ExpandItem(val id: Int): SavedRecipeEvent()
    data class RemoveItem(val id: Int): SavedRecipeEvent()
}


