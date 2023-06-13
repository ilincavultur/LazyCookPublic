package com.example.lazycookpublic.feature_recipe.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.lazycookpublic.feature_recipe.domain.model.ingredient.Ingredient


@Entity(
    tableName = "recipe_ingredient_table",
    foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["recipeId"],
            childColumns = ["recipe_id_foreign"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RecipeIngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val recipeIngredientId: Int? = null,
    @ColumnInfo(name = "recipe_id_foreign", index = true)
    var recipeIdForeign: Int? = null,
    val amount: Double? = 0.0,
    val recipeIngredientApiId: Int,
    val ingredientImageUrl: String = "",
    val original: String? = "", // name
)

fun RecipeIngredientEntity.toIngredient() : Ingredient {
    return Ingredient(
        amount = amount ?: 0.0,
        ingredientApiId = recipeIngredientApiId,
        ingredientImageUrl = ingredientImageUrl,
        original = original ?: ""
    )
}
