package com.ujizin.leafy.home.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.ui.components.EmptySection
import com.ujizin.leafy.core.ui.components.Section
import com.ujizin.leafy.core.ui.components.animated.AnimatedButtonIcon
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.components.card.CardPlant
import com.ujizin.leafy.core.ui.components.image.Icons
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.core.ui.extensions.share
import com.ujizin.leafy.domain.model.Plant
import com.ujizin.leafy.features.home.R

@Composable
internal fun HomeSection(
    nickname: String,
    plants: List<Plant>,
    onEmptyPlantClick: OnClick,
    onSearchClick: OnClick,
    onDrawerClick: OnClick,
    onPlantClick: (Long) -> Unit,
) {
    val context = LocalContext.current
    NavLazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item {
            Section(
                title =
                    stringResource(id = R.string.hello_user, nickname.capitalize()).capitalize(),
                subTitle = stringResource(id = R.string.welcome_back).capitalize(),
                leadingIcon = {
                    AnimatedButtonIcon(
                        icon = Icons.Hamburger,
                        animation = Animation.SlideToEnd,
                        onClick = onDrawerClick,
                    )
                },
                trailingIcon = {
                    AnimatedButtonIcon(
                        icon = Icons.Magnifier,
                        animation = Animation.SlideToStart,
                        onClick = onSearchClick,
                    )
                },
            )
        }
        when {
            plants.isEmpty() ->
                item {
                    EmptySection(
                        modifier = Modifier.padding(vertical = 32.dp, horizontal = 20.dp),
                        onClick = onEmptyPlantClick,
                    )
                }

            else ->
                items(plants, key = { it.id }) {
                    HomePlantCard(
                        plant = it,
                        onPlantClick = onPlantClick,
                        onSharedClick = { plant -> plant.share(context) },
                    )
                }
        }
    }
}

@Composable
fun NavLazyColumn(verticalArrangement: Arrangement.Vertical, content: LazyListScope.() -> Unit) {
    LazyColumn(
        verticalArrangement = verticalArrangement,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        content()
        item { Spacer(Modifier.padding(64.dp)) }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun LazyItemScope.HomePlantCard(
    plant: Plant,
    onPlantClick: (Long) -> Unit,
    onSharedClick: (Plant) -> Unit,
) {
    CardPlant(
        modifier =
            Modifier.animateItemPlacement()
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .aspectRatio(1F),
        plant = plant,
        onClick = { onPlantClick(plant.id) },
        onSharedClick = { onSharedClick(plant) },
    )
}
