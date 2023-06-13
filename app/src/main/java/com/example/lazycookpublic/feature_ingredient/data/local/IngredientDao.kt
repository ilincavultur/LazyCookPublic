package com.example.lazycookpublic.feature_ingredient.data.local

import androidx.room.*
import com.example.lazycookpublic.feature_ingredient.data.local.entity.IngredientEntity

@Dao
interface IngredientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(ingredients: List<IngredientEntity>)

    @Query("DELETE FROM ingredient_table WHERE id IN(:ingredientIds)")
    suspend fun deleteIngredients(ingredientIds: List<Int>)

    @Query("SELECT * FROM ingredient_table WHERE name LIKE '%' || :ingredient || '%'")
    suspend fun getIngredients(ingredient: String): List<IngredientEntity>

}