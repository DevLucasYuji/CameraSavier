package com.ujizin.leafy.preferences.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.material.color.DynamicColors
import com.ujizin.leafy.core.ui.components.Section
import com.ujizin.leafy.core.ui.components.selector.ButtonRow
import com.ujizin.leafy.core.ui.extensions.paddingScreen
import com.ujizin.leafy.core.ui.extensions.versionName
import com.ujizin.leafy.core.ui.local.LocalUser
import com.ujizin.leafy.domain.model.Language
import com.ujizin.leafy.domain.model.Theme
import com.ujizin.leafy.domain.model.User
import com.ujizin.leafy.domain.model.update
import com.ujizin.leafy.features.preferences.R
import com.ujizin.leafy.preferences.PreferencesViewModel
import com.ujizin.leafy.preferences.utils.rememberGoogleReview

@Composable
internal fun PreferencesRoute(viewModel: PreferencesViewModel = hiltViewModel()) {
    Section(modifier = Modifier.fillMaxSize(), title = stringResource(R.string.preferences_title)) {
        val user = LocalUser.current

        Spacer(Modifier.height(16.dp))
        PreferencesContent(
            user = user,
            onNicknameChanged = { nickname -> viewModel.update(user.update(nickname = nickname)) },
            onThemeChanged = { theme -> viewModel.update(user.update(theme = theme)) },
            onLanguageChanged = { language -> viewModel.update(user.update(language = language)) },
            onDynamicColorChanged = { dynamicColor ->
                viewModel.update(user.update(dynamicColor = dynamicColor))
            },
        )
    }
}

@Composable
internal fun PreferencesContent(
    modifier: Modifier = Modifier,
    user: User,
    onNicknameChanged: (String) -> Unit,
    onThemeChanged: (Theme) -> Unit,
    onLanguageChanged: (Language) -> Unit,
    onDynamicColorChanged: (Boolean) -> Unit,
) {
    Column(modifier) {
        val isDynamicColorAvailable = remember(DynamicColors::isDynamicColorAvailable)
        val googleReviewState = rememberGoogleReview()
        val context = LocalContext.current
        UserSelector(
            modifier = Modifier.fillMaxWidth().paddingScreen(vertical = 16.dp),
            nickname = user.nickname,
            onNicknameChanged = onNicknameChanged,
        )
        LanguageSelector(
            modifier = Modifier.fillMaxWidth().paddingScreen(vertical = 16.dp),
            language = user.settings.language,
            onLanguageChanged = onLanguageChanged,
        )
        ThemeSelector(
            modifier = Modifier.fillMaxWidth().paddingScreen(vertical = 16.dp),
            theme = user.settings.theme,
            onThemeChanged = onThemeChanged,
        )

        ButtonRow(
            modifier = Modifier.fillMaxWidth().paddingScreen(vertical = 16.dp),
            title = stringResource(R.string.rate_app),
            subTitle = context.versionName?.let { stringResource(R.string.version, it) },
            onClick = { googleReviewState.launch(context) },
        )

        if (isDynamicColorAvailable) {
            HorizontalDivider(modifier = Modifier.fillMaxWidth().paddingScreen(vertical = 16.dp))
            DynamicColorRow(
                modifier = Modifier.fillMaxWidth().paddingScreen(),
                dynamicColor = user.settings.dynamicColor,
                onDynamicColorChanged = onDynamicColorChanged,
            )
        }
    }
}
