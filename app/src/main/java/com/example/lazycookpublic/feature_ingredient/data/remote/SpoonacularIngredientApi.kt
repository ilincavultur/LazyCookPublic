package com.example.lazycookpublic.feature_ingredient.data.remote

import com.example.lazycookpublic.feature_ingredient.data.remote.dto.IngredientResponseDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SpoonacularIngredientApi {

    @Headers(
        "Accept: application/json"
    )
    @GET("search")
    suspend fun getIngredient(@Query("query") name: String, @Query("apiKey") apiKey: String) : IngredientResponseDto

}