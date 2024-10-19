package com.ujizin.leafy.user

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ujizin.leafy.MainUiState
import com.ujizin.leafy.MainViewModel
import com.ujizin.leafy.domain.model.Theme
import com.ujizin.leafy.domain.model.User

@Composable
fun rememberUserState(viewModel: MainViewModel = hiltViewModel()) =
    remember(viewModel) { viewModel.load() }
        .collectAsStateWithLifecycle(initialValue = MainUiState.Loading)

@Composable
fun User.isUserInDarkTheme(): Boolean {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    return remember(this) {
        when (settings.theme) {
            Theme.System -> isSystemInDarkTheme
            Theme.Dark -> true
            Theme.Light -> false
        }
    }
}
