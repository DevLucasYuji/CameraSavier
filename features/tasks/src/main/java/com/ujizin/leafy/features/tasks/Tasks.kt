package com.ujizin.leafy.features.tasks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ujizin.leafy.core.ui.annotation.ThemePreviews
import com.ujizin.leafy.core.ui.components.EmptySection
import com.ujizin.leafy.core.ui.components.Section
import com.ujizin.leafy.core.ui.components.image.Image
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.core.ui.extensions.dateFormatted
import com.ujizin.leafy.core.ui.extensions.displayNameRes
import com.ujizin.leafy.core.ui.extensions.getDisplayName
import com.ujizin.leafy.core.ui.extensions.paddingScreen
import com.ujizin.leafy.domain.model.WeekDay
import com.ujizin.leafy.features.tasks.model.Task

@Composable
fun TasksRoute(
    viewModel: TasksViewModel = hiltViewModel(),
    onPlantClick: (Long) -> Unit,
    onTakePictureClick: OnClick,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) { viewModel.loadAlarms() }

    Tasks(
        uiState = uiState,
        onPlantClick = onPlantClick,
        onTakePictureClick = onTakePictureClick,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Tasks(
    uiState: TasksUiState,
    onTakePictureClick: OnClick,
    onPlantClick: (Long) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            Section(title = stringResource(id = R.string.task_title))
        }

        when (uiState) {
            TasksUiState.Initial -> Unit
            TasksUiState.Empty -> item {
                EmptySection(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onTakePictureClick
                )
            }

            is TasksUiState.Success -> uiState.tasks.forEach { (weekDay, tasks) ->
                stickyHeader { HeaderWeekDay(weekDay) }
                items(tasks) { task ->
                    TaskItems(
                        modifier = Modifier
                            .fillMaxWidth()
                            .paddingScreen(vertical = 16.dp),
                        task = task,
                        onClick = {
                            onPlantClick(task.plant.id)
                        },
                    )
                }
            }
        }

        item { Spacer(Modifier.padding(64.dp)) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItems(
    modifier: Modifier = Modifier,
    task: Task,
    onClick: OnClick,
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            PlantItem(task = task)
            Text(
                text = task.alarm.dateFormatted,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

@Composable
private fun PlantItem(
    modifier: Modifier = Modifier,
    task: Task,
) {
    val context = LocalContext.current
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(80.dp)
                .clip(MaterialTheme.shapes.small),
            model = task.plant.file,
            contentDescription = task.plant.title
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(text = task.plant.title, style = MaterialTheme.typography.titleMedium)
            Text(text = task.alarm.weekDays.getDisplayName(context))
        }
    }
}

@Composable
fun HeaderWeekDay(weekDay: WeekDay) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .paddingScreen(vertical = 8.dp),
        text = stringResource(id = weekDay.displayNameRes).capitalize(),
        style = MaterialTheme.typography.titleMedium,
    )
}

@ThemePreviews
@Composable
private fun TasksPreview() {
}
