package com.example.lazycookpublic.feature_recipe.data.remote.dto.recipe

//import com.example.lazycook.feature_recipe.domain.model.recipe.RecipeIngredient


data class RecipeIngredientDto(
    val aisle: String,
    val amount: Double,
    val id: Int,
    val image: String,
    val meta: List<String>,
    val name: String,
    val original: String,
    val originalName: String,
    val unit: String,
    val unitLong: String,
    val unitShort: String
)

//fun RecipeIngredientDto.toRecipeIngredient() : RecipeIngredient {
//    return RecipeIngredient(
//        aisle, amount, id, image, meta, name, original, originalName, unitShort
//    )
//}