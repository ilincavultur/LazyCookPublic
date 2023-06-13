package com.example.lazycookpublic.feature_ingredient.presentation

sealed class IngredientEvent{
    data class OnRowClick(val id: Int) : IngredientEvent()
    object OnCancelClick : IngredientEvent()
}