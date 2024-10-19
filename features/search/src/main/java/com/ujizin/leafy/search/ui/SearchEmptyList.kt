package com.ujizin.leafy.search.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ujizin.leafy.core.components.R as CR
import com.ujizin.leafy.core.ui.components.EmptySection
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.features.search.R

@Composable
internal fun SearchEmptyList(
    searchText: String,
    modifier: Modifier = Modifier,
    onTakePictureClick: OnClick,
) {
    val context = LocalContext.current
    val description =
        remember(searchText) {
                if (searchText.isEmpty()) {
                    context.getString(CR.string.empty_plant)
                } else {
                    context.getString(R.string.search_empty_description, searchText)
                }
            }
            .capitalize()

    EmptySection(modifier = modifier, description = description, onClick = onTakePictureClick)
}
