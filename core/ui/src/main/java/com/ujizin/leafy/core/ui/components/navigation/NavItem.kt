package com.ujizin.leafy.core.ui.components.navigation

import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.ui.components.navigation.bottombar.BottomNavItem
import com.ujizin.leafy.core.ui.components.navigation.drawer.DrawerItem

/**
 * NavItem is used for components that needs to be navigated to one destination.
 *
 * Used in [BottomNavItem] & [DrawerItem].
 */
interface NavItem {
    /** Item destination. */
    val destination: Destination
}
