package com.example.lazycookpublic.feature_recipe.di

import com.example.lazycookpublic.core.util.LazyCookDatabase
import com.example.lazycookpublic.feature_recipe.data.remote.SpoonacularRecipeApi
import com.example.lazycookpublic.feature_recipe.data.repository.RecipeRepositoryImpl
import com.example.lazycookpublic.feature_recipe.domain.repository.RecipeRepository
import com.example.lazycookpublic.feature_recipe.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeModule {
    @Singleton
    @Provides
    fun provideSpoonacularRecipeApi(): SpoonacularRecipeApi = Retrofit.Builder()
        .baseUrl("https://api.spoonacular.com/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SpoonacularRecipeApi::class.java)

    @Provides
    @Singleton
    fun provideGetRecipeUseCase(repository: RecipeRepository) : GetRecipe {
        return GetRecipe(repository)
    }

    @Provides
    @Singleton
    fun provideRecipeUseCases(repository: RecipeRepository): RecipeUseCases {
        return RecipeUseCases(
            getRecipe = GetRecipe(repository),
            getSavedRecipes = GetSavedRecipes(repository),
            getSavedRecipe = GetSavedRecipe(repository),
            saveRecipe = SaveRecipeUseCase(repository),
            deleteRecipe = DeleteRecipeUseCase(repository),
            isRecipeBookmarked = IsRecipeBookmarkedUseCase(repository),
            getRecipeInfo = GetRecipeInfo(repository),
            getRecipeSteps = GetRecipeSteps(repository)
            //getRecipeIngredients = GetRecipeIngredients(repository)
        )
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(
        db: LazyCookDatabase,
        api: SpoonacularRecipeApi
    ) : RecipeRepository {
        return RecipeRepositoryImpl(api, db.recipeDao)
    }
}