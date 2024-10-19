package com.ujizin.leafy.core.ui.components.navigation.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.components.R
import com.ujizin.leafy.core.ui.components.header.HeaderTitle
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.core.ui.local.LocalUser

@Composable
internal fun DrawerContent(
    modifier: Modifier = Modifier,
    drawerItem: DrawerItem?,
    onUserClick: OnClick,
    onDrawerClicked: (DrawerItem) -> Unit,
    onCloseDrawer: () -> Unit,
) {
    ModalDrawerSheet(modifier) {
        Column(Modifier.padding(horizontal = 12.dp, vertical = 32.dp)) {
            HeaderTitle(
                modifier = Modifier.fillMaxWidth().clickable(onClick = onUserClick),
                title = LocalUser.current.nickname,
                subTitle = stringResource(R.string.edit_name).capitalize(),
            )
            HorizontalDivider(Modifier.padding(vertical = 16.dp))
            DrawerItems(
                drawerItems = remember { DrawerItem.entries.toList() },
                drawerItem = drawerItem,
                onDrawerClicked = onDrawerClicked,
                onCloseDrawer = onCloseDrawer,
            )
        }
    }
}

@Composable
private fun DrawerItems(
    drawerItems: List<DrawerItem>,
    drawerItem: DrawerItem?,
    onDrawerClicked: (DrawerItem) -> Unit,
    onCloseDrawer: () -> Unit,
) {
    drawerItems.forEach { item ->
        val isSelected = remember(drawerItem) { drawerItem?.destination == item.destination }
        NavigationDrawerItem(
            modifier = Modifier.padding(vertical = 4.dp),
            icon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onSurface,
                    painter = painterResource(id = item.iconRes),
                    contentDescription = null,
                )
            },
            label = { Text(text = stringResource(id = item.labelRes).capitalize()) },
            selected = isSelected,
            onClick = { if (isSelected) onCloseDrawer() else onDrawerClicked(item) },
        )
    }
}
