package com.ujizin.leafy.search.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ujizin.leafy.core.themes.LeafyTheme
import com.ujizin.leafy.core.ui.annotation.ThemePreviews
import com.ujizin.leafy.core.ui.components.Section
import com.ujizin.leafy.core.ui.components.animated.AnimatedButtonIcon
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.components.image.Icons
import com.ujizin.leafy.core.ui.components.textfield.TextField
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.core.ui.extensions.paddingScreen
import com.ujizin.leafy.core.ui.extensions.share
import com.ujizin.leafy.core.ui.state.keyboardAsState
import com.ujizin.leafy.features.search.R
import com.ujizin.leafy.search.SearchUiState
import com.ujizin.leafy.search.SearchViewModel
import com.ujizin.leafy.search.ui.components.SearchPlaceholder

@Composable
fun SearchRoute(
    onDrawerClick: OnClick,
    onTakePictureClick: OnClick,
    onPlantClick: (Long) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val isKeyboardOpen by keyboardAsState()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }
    var searchText by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(viewModel, searchText) { viewModel.search(searchText) }

    SearchContent(
        uiState = uiState,
        isKeyboardOpen = isKeyboardOpen,
        onDrawerClick = onDrawerClick,
        focusRequester = focusRequester,
        searchText = searchText,
        onPlantClick = onPlantClick,
        onTakePictureClick = onTakePictureClick,
        onSearchTextChanged = { searchText = it },
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SearchContent(
    uiState: SearchUiState,
    isKeyboardOpen: Boolean,
    onDrawerClick: OnClick,
    onTakePictureClick: OnClick,
    searchText: String,
    focusRequester: FocusRequester,
    onPlantClick: (Long) -> Unit,
    onSearchTextChanged: (String) -> Unit,
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Section(
                modifier = Modifier.fillMaxWidth().paddingScreen(),
                headerPaddingValues = PaddingValues(top = 32.dp),
                leadingIcon = if (!isKeyboardOpen) leadingIcon(onDrawerClick) else null,
                title = stringResource(R.string.search).capitalize(),
            )
        }

        stickyHeader {
            TextField(
                modifier =
                    Modifier.background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .paddingScreen(vertical = 16.dp)
                        .focusRequester(focusRequester),
                placeholder = { SearchPlaceholder() },
                value = searchText,
                onValueChange = onSearchTextChanged,
            )
        }

        when (val result: SearchUiState = uiState) {
            is SearchUiState.Initial ->
                item {
                    SearchLoading(
                        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                        autoFocus = result.autoFocus,
                        focusRequester = focusRequester,
                    )
                }

            SearchUiState.Empty ->
                item {
                    SearchEmptyList(
                        modifier = Modifier.padding(20.dp).fillMaxWidth(),
                        searchText = searchText,
                        onTakePictureClick = onTakePictureClick,
                    )
                }

            is SearchUiState.Loaded ->
                searchItems(
                    modifier =
                        Modifier.paddingScreen(vertical = 8.dp).fillMaxWidth().aspectRatio(1F),
                    data = result.items,
                    onPlantClick = onPlantClick,
                    onSharedClick = { plant -> plant.share(context) },
                )
        }
        item { Spacer(Modifier.padding(64.dp)) }
    }
}

private fun leadingIcon(onDrawerClick: OnClick): @Composable () -> Unit = {
    AnimatedButtonIcon(
        icon = Icons.Hamburger,
        animation = Animation.SlideToTop,
        onClick = onDrawerClick,
    )
}

@ThemePreviews
@Composable
private fun SearchPreview() {
    LeafyTheme {
        Surface {
            SearchContent(
                uiState = SearchUiState.Empty,
                isKeyboardOpen = false,
                onDrawerClick = {},
                onTakePictureClick = {},
                searchText = "",
                focusRequester = remember { FocusRequester() },
                onSearchTextChanged = {},
                onPlantClick = {},
            )
        }
    }
}
