package com.ujizin.leafy.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.components.R
import com.ujizin.leafy.core.themes.LeafyTheme
import com.ujizin.leafy.core.ui.components.button.Button
import com.ujizin.leafy.core.ui.components.image.Icons
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.capitalize

@Composable
fun EmptySection(
    modifier: Modifier = Modifier,
    description: String = stringResource(id = R.string.empty_plant).capitalize(),
    buttonTitle: String = stringResource(id = R.string.take_a_picture).capitalize(),
    enabled: Boolean = true,
    icons: Icons = Icons.Leaf,
    onClick: OnClick,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.defaultMinSize(minWidth = 128.dp, minHeight = 96.dp),
            painter = painterResource(id = icons.idRes),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            contentDescription = stringResource(id = icons.descriptionRes),
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = description.capitalize(),
            textAlign = TextAlign.Center,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
        Button(text = buttonTitle.capitalize(), enabled = enabled, onClick = onClick)
    }
}

@Preview
@Composable
fun PreviewEmptySection() {
    LeafyTheme { EmptySection {} }
}
