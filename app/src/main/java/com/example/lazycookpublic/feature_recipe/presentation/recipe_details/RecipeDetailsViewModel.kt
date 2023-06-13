package com.example.lazycookpublic.feature_recipe.presentation.recipe_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazycookpublic.core.util.Resource
import com.example.lazycookpublic.feature_recipe.domain.use_case.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val recipeUseCases: RecipeUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val recipeApiId: Int = checkNotNull(savedStateHandle["recipeApiId"])

    private val _state = mutableStateOf(RecipeDetailsState())
    val state: State<RecipeDetailsState> = _state

    private val _eventFlow = MutableSharedFlow<RecipeDetailsUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchIngredientsJob: Job? = null
    private var searchStepsJob: Job? = null

    init {

        searchIngredientsJob?.cancel()
        searchIngredientsJob = viewModelScope.launch {
            recipeUseCases.getRecipeInfo(recipeApiId)
                .onEach { result ->
                    when (result) {
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                name = result.data?.recipeInfoTitle ?: "",
                                imageUrl = result.data?.recipeInfoImageUrl ?: "",
                                ingredients = result.data?.ingredients ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(
                                RecipeDetailsUiEvent.ShowSnackbar(
                                    result.message ?: "Unknown Error"
                                )
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                name = result.data?.recipeInfoTitle ?: "",
                                imageUrl = result.data?.recipeInfoImageUrl ?: "",
                                ingredients = result.data?.ingredients ?: emptyList(),
                                isLoading = true
                            )
                        }
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                name = result.data?.recipeInfoTitle ?: "",
                                imageUrl = result.data?.recipeInfoImageUrl ?: "",
                                ingredients = result.data?.ingredients ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                }.launchIn(this)
        }

    }

    init {
        searchStepsJob?.cancel()
        searchStepsJob = viewModelScope.launch {
            recipeUseCases.getRecipeSteps(recipeApiId)
                .onEach { result ->
                    when (result) {
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                steps = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(
                                RecipeDetailsUiEvent.ShowSnackbar(
                                    result.message ?: "Unknown Error"
                                )
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                steps = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                steps = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                }.launchIn(this)
        }

    }

    fun onEvent (event: RecipeDetailsEvent) {
        when (event) {

        }
    }
}