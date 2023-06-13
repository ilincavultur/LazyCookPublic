package com.example.lazycookpublic.feature_recipe.data.local

import androidx.room.*
import com.example.lazycookpublic.feature_recipe.data.local.entity.RecipeEntity
import com.example.lazycookpublic.feature_recipe.data.local.entity.RecipeIngredientEntity
import com.example.lazycookpublic.feature_recipe.data.local.entity.StepEntity


@Dao
interface RecipeDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertRecipes(recipes: List<RecipeEntity>)
//
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeSteps(steps: List<StepEntity>)

    @Delete
    suspend fun deleteRecipeSteps(steps: List<StepEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeIngredients(ingredients: List<RecipeIngredientEntity>)

    @Delete
    suspend fun deleteRecipeIngredients(ingredients: List<RecipeIngredientEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity) : Long

    @Transaction
    suspend fun saveRecipe(recipe: RecipeEntity) { // save recipe on bookmarked
        insertRecipe(recipe)
    }

//    @Query("DELETE FROM recipe_table WHERE recipeId IN(:recipeIds)")
//    suspend fun deleteRecipes(recipeIds: List<Int>)

    @Query("DELETE FROM recipe_table WHERE recipeApiId=:id")
    suspend fun deleteRecipe(id: Int)

    @Query("SELECT * FROM recipe_table")
    suspend fun getAllRecipes(): List<RecipeEntity> // get bookmarked recipes

    @Query("SELECT * FROM recipe_table WHERE recipeApiId=:id")
    suspend fun getRecipe(id: Int): RecipeEntity // to check if recipe is bookmarked

    @Query("SELECT * FROM recipe_ingredient_table WHERE recipe_id_foreign=:id")
    suspend fun getRecipeIngredients(id: Int): List<RecipeIngredientEntity>

    @Query("SELECT * FROM recipe_step_table WHERE recipe_step_id_foreign=:id")
    suspend fun getRecipeSteps(id: Int): List<StepEntity>
//
//    @Query("SELECT * FROM recipe_table WHERE title LIKE '%' || :recipeTitle || '%'")
//    suspend fun getRecipes(recipeTitle: String): List<RecipeEntity>

}