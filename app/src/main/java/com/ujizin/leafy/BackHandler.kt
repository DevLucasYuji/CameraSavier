package com.ujizin.leafy

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun BackHandler(navController: NavHostController, drawerState: DrawerState, onFinish: () -> Unit) {
    val scope = rememberCoroutineScope()
    val isDrawerOpen by rememberUpdatedState(drawerState.isOpen)

    when {
        isDrawerOpen ->
            androidx.activity.compose.BackHandler { scope.launch { drawerState.close() } }
        else ->
            androidx.activity.compose.BackHandler { if (!navController.navigateUp()) onFinish() }
    }
}
