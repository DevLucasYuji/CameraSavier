package com.ujizin.leafy.search

import androidx.compose.runtime.Immutable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.usecase.plant.find.FindPlantUseCase
import com.ujizin.leafy.domain.usecase.plant.load.LoadAllPlantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@HiltViewModel
class SearchViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val loadPlants: LoadAllPlantUseCase,
    private val findPlant: FindPlantUseCase,
) : ViewModel() {

    private val initAutoFocus: Boolean = savedStateHandle.toRoute<Destination.Search>().autoFocus

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Initial(initAutoFocus))
    val uiState = _uiState.asStateFlow()

    fun search(sentence: String) {
        when {
                sentence.isBlank() -> loadPlants()
                else -> findPlant(sentence)
            }
            .onEachPlant()
            .launchIn(viewModelScope)
    }

    private fun Flow<Result<List<Plant>>>.onEachPlant() = onEach { result ->
        _uiState.update { currentState ->
            when (result) {
                is Result.Error -> SearchUiState.Initial(initAutoFocus)
                Result.Loading -> currentState
                is Result.Success -> {
                    if (result.data.isEmpty()) {
                        SearchUiState.Empty
                    } else {
                        SearchUiState.Loaded(result.data)
                    }
                }
            }
        }
    }
}

sealed class SearchUiState(val items: List<Plant>) {
    @Immutable data class Initial(val autoFocus: Boolean = false) : SearchUiState(emptyList())

    @Immutable data object Empty : SearchUiState(emptyList())

    @Immutable data class Loaded(private val data: List<Plant>) : SearchUiState(data)
}
