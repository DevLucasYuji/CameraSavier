package com.ujizin.leafy

import androidx.lifecycle.ViewModel
import com.ujizin.leafy.domain.model.User
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.usecase.user.LoadUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadUser: LoadUser,
) : ViewModel() {

    fun load(): Flow<MainUiState> = loadUser()
        .map { result ->
            when (result) {
                is Result.Success -> MainUiState.Initialized(result.data)
                else -> MainUiState.Loading
            }
        }
}

sealed interface MainUiState {
    object Loading : MainUiState
    data class Initialized(val user: User) : MainUiState
}
