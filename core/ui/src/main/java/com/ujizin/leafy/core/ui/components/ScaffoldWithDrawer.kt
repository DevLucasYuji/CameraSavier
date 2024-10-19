package com.ujizin.leafy.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold as MaterialScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.ui.components.navigation.bottombar.NavigationBar
import com.ujizin.leafy.core.ui.components.navigation.bottombar.navigateToItem
import com.ujizin.leafy.core.ui.components.navigation.currentNavItemAsState
import com.ujizin.leafy.core.ui.components.navigation.drawer.DrawerContent
import com.ujizin.leafy.core.ui.components.navigation.drawer.DrawerItem
import kotlinx.coroutines.launch

@Composable
fun ScaffoldWithDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    navController: NavController,
    content: @Composable BoxScope.() -> Unit,
) {
    val currentDrawerItem by navController.currentNavItemAsState<DrawerItem>()
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = currentDrawerItem != null,
        drawerContent = {
            val scope = rememberCoroutineScope()
            DrawerContent(
                drawerItem = currentDrawerItem,
                onUserClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(Destination.Preferences)
                },
                onDrawerClicked = { drawerItem ->
                    scope.launch { drawerState.close() }
                    navController.navigateToItem(drawerItem)
                },
                onCloseDrawer = { scope.launch { drawerState.close() } },
            )
        },
    ) {
        MaterialScaffold(modifier = modifier, bottomBar = { NavigationBar(navController) }) {
            innerPadding ->
            Box(
                modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                content = content,
            )
        }
    }
}
