package com.ujizin.leafy.core.ui.components.navigation.bottombar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.ui.components.animated.animation.Animate.Animated
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.components.button.CameraButton
import com.ujizin.leafy.core.ui.components.navigation.NavItem
import com.ujizin.leafy.core.ui.components.navigation.currentNavItemAsState
import com.ujizin.leafy.core.ui.state.keyboardAsState

@Composable
internal fun NavigationBar(navController: NavController) {
    val navItem by navController.currentNavItemAsState<BottomNavItem>(BottomNavItem.Home)
    val isKeyboardOpen by keyboardAsState()
    val isBottomNavItem =
        remember(navItem) {
            BottomNavItem.entries.any { bottomNavItem ->
                bottomNavItem.destination != Destination.Camera &&
                    bottomNavItem.destination == navItem?.destination
            }
        }

    BottomNavigationBar(
        bottomNavItem = navItem,
        showBottomNavigation = isBottomNavItem && !isKeyboardOpen,
        bottomNavItems = remember { BottomNavItem.entries.toList() },
        onNavItemClicked = { destination -> navController.navigateToItem(destination) },
    )
}

@Composable
private fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    bottomNavItem: BottomNavItem?,
    showBottomNavigation: Boolean,
    bottomNavItems: List<BottomNavItem>,
    onNavItemClicked: (BottomNavItem) -> Unit,
) {
    Box(modifier = modifier) {
        Animated(
            visibleTarget = showBottomNavigation,
            animation = Animation.SlideToTop.copy(durationMillis = Animation.SMALL_DURATION),
        ) {
            androidx.compose.material3.NavigationBar {
                bottomNavItems.forEach { item ->
                    NavBarItem(
                        selectedItem = bottomNavItem,
                        item = item,
                        onClick = { onNavItemClicked(item) },
                    )
                }
            }

            CameraButton(modifier = Modifier.fillMaxWidth().offset(y = (-32).dp)) {
                onNavItemClicked(BottomNavItem.Camera)
            }
        }
    }
}

fun NavController.navigateToItem(item: NavItem) {
    navigate(item.destination) {
        graph.startDestinationRoute?.let { route -> popUpTo(route) { saveState = true } }
        launchSingleTop = true
        restoreState = true
    }
}
