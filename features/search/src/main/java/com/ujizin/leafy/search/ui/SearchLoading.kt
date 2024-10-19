package com.ujizin.leafy.search.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp

@Composable
internal fun SearchLoading(
    autoFocus: Boolean,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(autoFocus) { if (autoFocus) focusRequester.requestFocus() }

    Box(modifier = modifier) {
        CircularProgressIndicator(modifier = Modifier.size(32.dp).align(Alignment.Center))
    }
}
