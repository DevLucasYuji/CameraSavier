package br.com.devlucasyuji.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.devlucasyuji.components.extensions.section
import br.com.devlucasyuji.components.ui.Section

@Composable
fun SettingsSection() {
    Box(Modifier.fillMaxSize()) {
        Section(
            modifier = Modifier.section(),
            title = "Settings"
        )
    }
}
