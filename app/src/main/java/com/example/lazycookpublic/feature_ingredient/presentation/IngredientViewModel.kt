package com.example.lazycookpublic.feature_ingredient.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazycookpublic.core.util.Resource
import com.example.lazycookpublic.feature_ingredient.domain.model.Ingredient
import com.example.lazycookpublic.feature_ingredient.domain.use_case.GetIngredient
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
class IngredientViewModel @Inject constructor(
    private val getIngredient: GetIngredient
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(IngredientState())
    val state: State<IngredientState> = _state

    private val _eventFlow = MutableSharedFlow<IngredientUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query
        Log.d(TAG, "onSearch: _searchQuery.value "  + query)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getIngredient(query)
                .onEach { result ->
                    when(result) {
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                ingredientItems = result.data?.map {
                                    Ingredient(
                                        it.id,
                                        it.image,
                                        it.name,
                                        ingredientIsInList(it.id)
                                    )
                                } ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(
                                IngredientUiEvent.ShowSnackbar(
                                result.message ?: "Unknown Error"
                            ))
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                ingredientItems = result.data?.map {
                                    Ingredient(
                                        it.id,
                                        it.image,
                                        it.name,
                                        ingredientIsInList(it.id)
                                    )
                                } ?: emptyList(),
                                isLoading = true
                            )
                        }
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                ingredientItems = result.data?.map {
                                    Ingredient(
                                        it.id,
                                        it.image,
                                        it.name,
                                        ingredientIsInList(it.id)
                                    )
                                } ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    fun onEvent(event: IngredientEvent) {
        when (event) {
            is IngredientEvent.OnRowClick -> {

                _state.value = state.value.copy(
                    ingredientItems = state.value.ingredientItems.map {
                        if (it.id == event.id) {
                            Ingredient(
                                it.id,
                                it.image,
                                it.name,
                                !it.isSelected
                            )
                        } else {
                            it
                        }
                    }
                )

                _state.value = state.value.copy(
                    selectedIngredientItems = state.value.selectedIngredientItems.filter {
                        it.id != event.id
                    }
                )

                _state.value = state.value.copy(
                    selectedIngredientItems = state.value.selectedIngredientItems +
                            state.value.ingredientItems.filter {
                                it.isSelected && !ingredientIsInList(it.id)
                            }

                )

                state.value.selectedIngredientItems.forEach {
                    Log.d(TAG, "onRowClick: selected items: " + it.name)
                }

            }
            IngredientEvent.OnCancelClick -> {
                _state.value = state.value.copy(
                    ingredientItems = state.value.ingredientItems.map {
                        Ingredient(
                            it.id,
                            it.image,
                            it.name,
                            false
                        )
                    }
                )

                _state.value = state.value.copy(
                    selectedIngredientItems = emptyList()
                )
            }
        }
    }

    fun ingredientIsInList(id: Int) : Boolean {
        _state.value.selectedIngredientItems.forEach {
            if (it.id == id) {
                return true
            }
        }
        return false
    }

    fun getIngredientsString(list: List<Ingredient>) : String {
        var ingredientString : String = ""

        list.forEach {
            ingredientString += it.name
            ingredientString += ","
        }
        return ingredientString
    }
}