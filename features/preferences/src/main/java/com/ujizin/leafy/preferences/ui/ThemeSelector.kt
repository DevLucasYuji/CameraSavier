package com.ujizin.leafy.preferences.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.ujizin.leafy.core.ui.components.selector.ModalSelector
import com.ujizin.leafy.core.ui.components.selector.ModalValue
import com.ujizin.leafy.core.ui.components.selector.Selector
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.domain.model.Theme
import com.ujizin.leafy.features.preferences.R
import com.ujizin.leafy.preferences.extensions.displayResId

@Composable
fun ThemeSelector(modifier: Modifier = Modifier, theme: Theme, onThemeChanged: (Theme) -> Unit) {
    val context = LocalContext.current
    var showModal by remember { mutableStateOf(false) }
    val themes = remember {
        Theme.entries.map { ModalValue(context.getString(it.displayResId), it) }
    }
    val currentTheme = remember(theme) { ModalValue(context.getString(theme.displayResId), theme) }

    Selector(
        modifier = modifier,
        title = stringResource(R.string.app_theme).capitalize(),
        subTitle = stringResource(theme.displayResId),
        showModal = showModal,
        onModalStateChanged = { showModal = it },
    ) {
        ModalSelector(
            title = stringResource(R.string.app_theme).capitalize(),
            currentValue = currentTheme,
            values = themes,
            onValueChanged = { onThemeChanged(it.value) },
        )
    }
}
