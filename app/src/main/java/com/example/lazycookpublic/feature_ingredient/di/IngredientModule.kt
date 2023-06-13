package com.example.lazycookpublic.feature_ingredient.di

import android.content.Context

import androidx.room.Room
import com.example.lazycookpublic.core.util.LazyCookDatabase

import com.example.lazycookpublic.feature_ingredient.data.remote.SpoonacularIngredientApi
import com.example.lazycookpublic.feature_ingredient.data.repository.IngredientRepositoryImpl
import com.example.lazycookpublic.feature_ingredient.domain.repository.IngredientRepository
import com.example.lazycookpublic.feature_ingredient.domain.use_case.GetIngredient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IngredientModule {
    @Singleton
    @Provides
    fun provideSpoonacularIngredientApi(): SpoonacularIngredientApi = Retrofit.Builder()
        .baseUrl("https://api.spoonacular.com/food/ingredients/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SpoonacularIngredientApi::class.java)

    @Provides
    @Singleton
    fun provideGetIngredientUseCase(repository: IngredientRepository) : GetIngredient {
        return GetIngredient(repository)
    }

    @Provides
    @Singleton
    fun provideIngredientRepository(
        db: LazyCookDatabase,
        api: SpoonacularIngredientApi
    ) : IngredientRepository {
        return IngredientRepositoryImpl(api, db.ingredientDao)
    }

    @Provides
    @Singleton
    fun provideLazyCookDatabase(
        @ApplicationContext appContext: Context
    ) : LazyCookDatabase {
        return Room.databaseBuilder(
            appContext, LazyCookDatabase::class.java, "lazy_cook"
        ).fallbackToDestructiveMigration().build()
    }

}