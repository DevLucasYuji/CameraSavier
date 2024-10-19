package com.ujizin.leafy

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import com.ujizin.leafy.core.themes.LeafyTheme
import com.ujizin.leafy.core.ui.components.AppPermission
import com.ujizin.leafy.core.ui.extensions.Content
import com.ujizin.leafy.core.ui.local.LocalUser
import com.ujizin.leafy.user.isUserInDarkTheme
import com.ujizin.leafy.user.rememberUserState
import com.ujizin.leafy.user.setLanguage

@Composable
fun AppCompatActivity.AppConfiguration(content: @Composable Content) {
    val userState by rememberUserState()
    when (val state: MainUiState = userState) {
        is MainUiState.Initialized -> {
            val user = state.user
            val darkTheme = user.isUserInDarkTheme()
            val language by rememberUpdatedState(user.settings.language)
            val dynamicColor by rememberUpdatedState(user.settings.dynamicColor)

            LaunchedEffect(language) { setLanguage(language) }

            CompositionLocalProvider(LocalUser provides user) {
                AppPermission {
                    LeafyTheme(
                        darkTheme = darkTheme,
                        dynamicColor = dynamicColor,
                        content = content,
                    )
                }
            }
        }

        MainUiState.Loading -> Unit
    }
}
