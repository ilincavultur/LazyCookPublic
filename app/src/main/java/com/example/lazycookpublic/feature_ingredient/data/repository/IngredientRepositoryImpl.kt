package com.example.lazycookpublic.feature_ingredient.data.repository

import com.example.lazycookpublic.BuildConfig
import com.example.lazycookpublic.core.util.Resource
import com.example.lazycookpublic.feature_ingredient.data.local.IngredientDao
import com.example.lazycookpublic.feature_ingredient.data.local.entity.toIngredient
import com.example.lazycookpublic.feature_ingredient.data.remote.SpoonacularIngredientApi
import com.example.lazycookpublic.feature_ingredient.data.remote.dto.toIngredientEntity
import com.example.lazycookpublic.feature_ingredient.domain.model.Ingredient
import com.example.lazycookpublic.feature_ingredient.domain.repository.IngredientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class IngredientRepositoryImpl(
    private val api: SpoonacularIngredientApi,
    private val dao: IngredientDao
) : IngredientRepository {
    override fun getIngredients(ingredient: String): Flow<Resource<List<Ingredient>>> = flow {
        emit(Resource.Loading())

        val ingredients = dao.getIngredients(ingredient).map {
            it.toIngredient()
        }

        emit(Resource.Loading(data = ingredients))

        try {
            val remoteIngredients = api.getIngredient(ingredient, BuildConfig.SPOONACULAR_INGREDIENT_API_KEY)
            dao.deleteIngredients(remoteIngredients.results.map {
                it.id
            })
            dao.insertIngredients(remoteIngredients.results.map {
                it.toIngredientEntity()
            })
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Ooops something went wrong", data = ingredients))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Check internet connection", data = ingredients))
        }

        val newIngredients = dao.getIngredients(ingredient).map {
            it.toIngredient()
        }
        emit(Resource.Success(newIngredients))
    }


}