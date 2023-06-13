package com.example.lazycookpublic.feature_recipe.presentation

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazycookpublic.core.util.Resource
import com.example.lazycookpublic.feature_recipe.domain.model.recipe.Recipe
import com.example.lazycookpublic.feature_recipe.domain.use_case.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeUseCases: RecipeUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val ingredients: String = checkNotNull(savedStateHandle["ingredientsString"])

    private val _state = mutableStateOf(RecipeState())
    val state: State<RecipeState> = _state

    private val _eventFlow = MutableSharedFlow<RecipeUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    init {
        Log.d(ContentValues.TAG, "onSearch: _searchIngredientsQuery.value "  + ingredients)

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            recipeUseCases.getRecipe(ingredients)
                .onEach { result ->
                    when(result) {
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                recipeItems = result.data?.map {
                                    Recipe(
                                        it.recipeId,
                                        it.id,
                                        it.image,
                                        it.title,
                                        isRecipeBookmarked(it.id),
                                    )
                                } ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(
                                RecipeUiEvent.ShowSnackbar(
                                    result.message ?: "Unknown Error"
                                ))
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                recipeItems = result.data?.map {
                                    Recipe(
                                        it.recipeId,
                                        it.id,
                                        it.image,
                                        it.title,
                                        isRecipeBookmarked(it.id),
                                    )
                                } ?: emptyList(),
                                isLoading = true
                            )
                        }
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                recipeItems = result.data?.map {
                                    Recipe(
                                        it.recipeId,
                                        it.id,
                                        it.image,
                                        it.title,
                                        isRecipeBookmarked(it.id),
                                    )
                                } ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    fun onEvent (event: RecipeEvent) {
        when (event) {
            is RecipeEvent.OnRowClick -> {

            }
            is RecipeEvent.OnBookmarkClick -> {
                if (event.recipe.isBookmarked) {
                    viewModelScope.launch {
                        recipeUseCases.saveRecipe(event.recipe)
                    }
                } else {
                    viewModelScope.launch {
                        recipeUseCases.deleteRecipe(event.recipe.id)
                    }
                }

            }
        }
    }

    suspend fun isRecipeBookmarked(id : Int) : Boolean { // if we find the item id (not entity id) in the db
        recipeUseCases.isRecipeBookmarked(id).let {
            if (it == null) {
                return false
            }
        }
        return true
    }
}