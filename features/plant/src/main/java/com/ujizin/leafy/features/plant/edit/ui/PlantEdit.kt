package com.ujizin.leafy.features.plant.edit.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.features.plant.edit.PlantEditUiState
import com.ujizin.leafy.features.plant.edit.PlantEditViewModel

@Composable
fun PlantEditRoute(onBackPressed: OnClick, viewModel: PlantEditViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) { viewModel.loadEditPlant() }

    PlantEdit(uiState = uiState, onBackPressed = onBackPressed)
}

@Composable private fun PlantEdit(uiState: PlantEditUiState, onBackPressed: OnClick) {}
