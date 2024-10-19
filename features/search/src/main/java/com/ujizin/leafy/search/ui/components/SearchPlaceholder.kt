package com.ujizin.leafy.search.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.ui.components.animated.AnimatedIcon
import com.ujizin.leafy.core.ui.components.image.Icons
import com.ujizin.leafy.core.ui.components.textfield.Placeholder
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.features.search.R

@Composable
internal fun SearchPlaceholder(modifier: Modifier = Modifier) {
    Placeholder(
        modifier = modifier,
        leadingIcon = {
            AnimatedIcon(
                modifier = Modifier.padding(end = 8.dp).size(24.dp),
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5F),
                icon = Icons.Magnifier,
            )
        },
        text = stringResource(id = R.string.search).capitalize(),
    )
}
