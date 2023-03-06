package br.com.devlucasyuji.components.ui.navigation.bottombar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.devlucasyuji.components.ui.animated.animation.Animate.Animated
import br.com.devlucasyuji.components.ui.animated.animation.Animation
import br.com.devlucasyuji.components.ui.button.CameraButton
import br.com.devlucasyuji.components.ui.navigation.currentNavItemAsState
import br.com.devlucasyuji.navigation.Destination
import br.com.devlucasyuji.navigation.navigate

@Composable
internal fun NavigationBar(navController: NavController) {
    val navItem by navController.currentNavItemAsState<BottomNavItem>()
    val hideBottomNavigationBar = remember(navItem) {
        BottomNavItem.values().none { destination ->
            destination.destination != Destination.Camera && destination.destination == navItem?.destination
        }
    }

    if (hideBottomNavigationBar) return

    BottomNavigationBar(
        bottomNavItem = navItem,
        bottomNavItems = remember { BottomNavItem.values().toList() },
        onNavItemClicked = { destination ->
            navController.navigateToItem(destination)
        }
    )
}

@Composable
private fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    bottomNavItem: BottomNavItem?,
    bottomNavItems: List<BottomNavItem>,
    onNavItemClicked: (BottomNavItem) -> Unit,
) {
    Box(modifier = modifier) {
        Animated(animation = Animation.SlideToTop.copy(delayMillis = Animation.SmallDelay)) {
            androidx.compose.material3.NavigationBar {
                bottomNavItems.forEach { item ->
                    NavBarItem(selectedItem = bottomNavItem, item = item, onClick = {
                        onNavItemClicked(item)
                    })
                }
            }
        }
        CameraButton(Modifier.offset(y = (-32).dp)) {
            onNavItemClicked(BottomNavItem.Camera)
        }
    }
}

private fun NavController.navigateToItem(item: BottomNavItem) {
    if (currentBackStackEntry?.destination?.route == item.destination.route) return

    navigate(item.destination) {
        launchSingleTop = false
        restoreState = true
    }
}
