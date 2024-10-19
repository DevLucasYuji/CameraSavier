package com.ujizin.leafy.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ujizin.leafy.core.themes.LeafyTheme
import com.ujizin.leafy.core.ui.annotation.ThemePreviews
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.local.LocalUser
import com.ujizin.leafy.home.HomeUIState
import com.ujizin.leafy.home.HomeViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onTakePictureClick: OnClick,
    onDrawerClick: OnClick,
    onSearchClick: OnClick,
    onPlantClick: (id: Long) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) { viewModel.loadHome() }

    HomeContent(
        modifier = Modifier.fillMaxSize(),
        state = state,
        nickname = LocalUser.current.nickname,
        onTakePictureClick = onTakePictureClick,
        onSearchClick = onSearchClick,
        onDrawerClick = onDrawerClick,
        onPlantClick = onPlantClick,
    )
}

@Composable
private fun HomeContent(
    state: HomeUIState,
    nickname: String,
    onTakePictureClick: OnClick,
    onSearchClick: OnClick,
    onDrawerClick: OnClick,
    modifier: Modifier = Modifier,
    onPlantClick: (Long) -> Unit,
) {
    Box(modifier = modifier, contentAlignment = Alignment.TopCenter) {
        when (val result: HomeUIState = state) {
            HomeUIState.Loading -> {}
            is HomeUIState.Success ->
                HomeSection(
                    nickname = nickname,
                    plants = result.plants,
                    onEmptyPlantClick = onTakePictureClick,
                    onSearchClick = onSearchClick,
                    onDrawerClick = onDrawerClick,
                    onPlantClick = onPlantClick,
                )

            is HomeUIState.Error -> {}
        }
    }
}

@ThemePreviews
@Composable
private fun HomeContentPreview() {
    LeafyTheme {
        Surface {
            HomeContent(
                state = HomeUIState.Success(listOf()),
                nickname = "User",
                onTakePictureClick = {},
                onSearchClick = {},
                onDrawerClick = {},
                onPlantClick = {},
            )
        }
    }
}
