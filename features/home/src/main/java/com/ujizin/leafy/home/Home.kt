package com.ujizin.leafy.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ujizin.leafy.core.themes.LeafyTheme
import com.ujizin.leafy.core.ui.annotation.ThemePreviews
import com.ujizin.leafy.core.ui.extensions.OnClick

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onTakePictureClick: OnClick,
    onDrawerClick: OnClick,
    onSearchClick: OnClick
) {
    val state by viewModel.homeState.collectAsState()

    Home(
        state = state,
        onTakePictureClick = onTakePictureClick,
        onSearchClick = onSearchClick,
        onDrawerClick = onDrawerClick
    )
}

@Composable
private fun Home(
    state: HomeUIState,
    onTakePictureClick: OnClick,
    onSearchClick: OnClick,
    onDrawerClick: OnClick,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        when (val result: HomeUIState = state) {
            HomeUIState.Loading -> {}
            is HomeUIState.Success -> HomeSection(
                nickname = result.nickname,
                plants = result.plants,
                onEmptyPlantClick = onTakePictureClick,
                onSearchClick = onSearchClick,
                onDrawerClick = onDrawerClick,
            )

            is HomeUIState.Error -> {}
        }
    }
}

@ThemePreviews
@Composable
private fun PreviewHome() {
    LeafyTheme {
        Surface {
            Home(
                state = HomeUIState.Success("ujizin", listOf()),
                onTakePictureClick = {},
                onSearchClick = {},
                onDrawerClick = {}
            )
        }
    }
}
