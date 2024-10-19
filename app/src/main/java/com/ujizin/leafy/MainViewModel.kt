package com.ujizin.leafy

import androidx.lifecycle.ViewModel
import com.ujizin.leafy.domain.model.User
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.usecase.user.load.LoadUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@HiltViewModel
class MainViewModel @Inject constructor(private val loadUser: LoadUserUseCase) : ViewModel() {

    fun load(): Flow<MainUiState> =
        loadUser().map { result ->
            when (result) {
                is Result.Success -> MainUiState.Initialized(result.data)
                else -> MainUiState.Loading
            }
        }
}

sealed interface MainUiState {
    data object Loading : MainUiState

    data class Initialized(val user: User) : MainUiState
}
