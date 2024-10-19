package com.ujizin.leafy.features.plant.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.ui.components.animated.AnimatedButtonIcon
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.components.card.CardImage
import com.ujizin.leafy.core.ui.components.image.Icons
import com.ujizin.leafy.core.ui.components.scaffold.Scaffold
import com.ujizin.leafy.core.ui.components.scaffold.TopAppBar
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.core.ui.extensions.paddingScreen
import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.model.Plant

@Composable
internal fun PlantDetailsContent(
    onBackPressed: OnClick,
    plant: Plant,
    alarms: List<Alarm>,
    onAlarmChanged: (Alarm) -> Unit,
    onDeleteClick: (Plant) -> Unit,
    onSharedClick: (Plant) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                onNavigationContent = {
                    AnimatedButtonIcon(
                        icon = Icons.Back,
                        size = 24.dp,
                        onClick = onBackPressed,
                        animation = Animation.SlideToTop,
                    )
                },
                onActionContent = {
                    PlantDropDown(
                        animation = Animation.SlideToTop,
                        onDeleteClick = { onDeleteClick(plant) },
                    )
                },
            )
        }
    ) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            CardImage(
                modifier = Modifier.fillMaxWidth().aspectRatio(1F),
                animation = Animation.SlideToTop,
                data = plant.filePath,
                elevation = 0.dp,
                shape = RectangleShape,
                contentDescription = plant.title,
            )
            PlantColumn(
                modifier =
                    Modifier.absoluteOffset(y = (-24).dp)
                        .background(
                            MaterialTheme.colorScheme.background,
                            RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                        ),
                plant = plant,
                alarms = alarms,
                onAlarmChanged = onAlarmChanged,
                onSharedClick = onSharedClick,
            )
        }
    }
}

@Composable
fun PlantColumn(
    plant: Plant,
    alarms: List<Alarm>,
    onAlarmChanged: (Alarm) -> Unit,
    onSharedClick: (Plant) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        PlantContent(
            modifier = Modifier.fillMaxWidth().paddingScreen(vertical = 24.dp),
            title = plant.title.capitalize(),
            onSharedClick = { onSharedClick(plant) },
            description = plant.description.capitalize(),
        )
        HorizontalDivider(modifier = Modifier.paddingScreen())
        PlantAlarmsContent(
            modifier = Modifier.fillMaxWidth().paddingScreen(vertical = 32.dp),
            alarms = alarms,
            onAlarmChanged = onAlarmChanged,
        )
    }
}
