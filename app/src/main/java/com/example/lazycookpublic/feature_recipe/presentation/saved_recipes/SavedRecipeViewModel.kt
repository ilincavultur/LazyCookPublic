package com.example.lazycookpublic.feature_recipe.presentation.saved_recipes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazycookpublic.core.util.Resource
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
class SavedRecipeViewModel @Inject constructor(
    private val recipeUseCases: RecipeUseCases
) : ViewModel() {
    private val _state = mutableStateOf(SavedRecipeState())
    val state: State<SavedRecipeState> = _state

    private val _eventFlow = MutableSharedFlow<SavedRecipeUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    init {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            recipeUseCases.getSavedRecipes()
                .onEach { result ->
                    when(result) {
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                savedRecipeItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(
                                SavedRecipeUiEvent.ShowSnackbar(
                                    result.message ?: "Unknown Error"
                                ))
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                savedRecipeItems = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                savedRecipeItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                }.launchIn(this)
        }

    }

    fun onEvent(event: SavedRecipeEvent) {
        when (event) {
            is SavedRecipeEvent.CollapseItem -> {
                if (!state.value.revealedRecipeItemIds.contains(event.id)) return
                _state.value = state.value.copy(
                    revealedRecipeItemIds = state.value.revealedRecipeItemIds.toMutableList().also { list ->
                        list.remove(event.id)
                    }
                )
            }
            is SavedRecipeEvent.ExpandItem -> {
                if (state.value.revealedRecipeItemIds.contains(event.id)) return
                _state.value = state.value.copy(
                    revealedRecipeItemIds = state.value.revealedRecipeItemIds.toMutableList().also { list ->
                        list.add(event.id)
                    }
                )
            }
            is SavedRecipeEvent.RemoveItem -> {
                viewModelScope.launch {
                    recipeUseCases.deleteRecipe(event.id)

                    recipeUseCases.getSavedRecipes()
                        .onEach { result ->
                            when(result) {
                                is Resource.Error -> {
                                    _state.value = state.value.copy(
                                        savedRecipeItems = result.data ?: emptyList(),
                                        isLoading = false
                                    )
                                    _eventFlow.emit(
                                        SavedRecipeUiEvent.ShowSnackbar(
                                            result.message ?: "Unknown Error"
                                        ))
                                }
                                is Resource.Loading -> {
                                    _state.value = state.value.copy(
                                        savedRecipeItems = result.data ?: emptyList(),
                                        isLoading = true
                                    )
                                }
                                is Resource.Success -> {
                                    _state.value = state.value.copy(
                                        savedRecipeItems = result.data ?: emptyList(),
                                        isLoading = false
                                    )
                                }
                            }
                        }.launchIn(this)
                }
            }
        }
    }
}