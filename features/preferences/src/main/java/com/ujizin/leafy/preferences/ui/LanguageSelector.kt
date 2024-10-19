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
import com.ujizin.leafy.domain.model.Language
import com.ujizin.leafy.features.preferences.R
import com.ujizin.leafy.preferences.extensions.displayResId
import com.ujizin.leafy.preferences.model.getLanguages

@Composable
fun LanguageSelector(
    modifier: Modifier = Modifier,
    language: Language,
    onLanguageChanged: (Language) -> Unit,
) {
    var showModal by remember { mutableStateOf(false) }
    Selector(
        modifier = modifier,
        title = stringResource(R.string.language),
        showModal = showModal,
        onModalStateChanged = { showModal = it },
        subTitle = stringResource(language.displayResId),
    ) {
        val context = LocalContext.current
        val languages =
            remember(language) {
                getLanguages(context).map { ModalValue(it.displayName, it.language) }
            }
        val currentLanguage =
            remember(language) { ModalValue(context.getString(language.displayResId), language) }

        ModalSelector(
            title = stringResource(R.string.language),
            currentValue = currentLanguage,
            values = languages,
            onValueChanged = { language -> onLanguageChanged(language.value) },
        )
    }
}
