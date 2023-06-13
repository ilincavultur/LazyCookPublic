package com.example.lazycookpublic.core.util

import androidx.room.RoomDatabase
import com.example.lazycookpublic.feature_ingredient.data.local.IngredientDao
import com.example.lazycookpublic.feature_ingredient.data.local.entity.IngredientEntity
import com.example.lazycookpublic.feature_recipe.data.local.RecipeDao
import com.example.lazycookpublic.feature_recipe.data.local.entity.RecipeEntity
import com.example.lazycookpublic.feature_recipe.data.local.entity.RecipeIngredientEntity
import com.example.lazycookpublic.feature_recipe.data.local.entity.StepEntity

@androidx.room.Database(entities = [IngredientEntity::class, RecipeEntity::class, RecipeIngredientEntity::class, StepEntity::class], version = 2)
abstract class LazyCookDatabase : RoomDatabase() {
    abstract val ingredientDao: IngredientDao
    abstract val recipeDao: RecipeDao
    //abstract val recipeIngredientDao: RecipeIngredientDao
}
