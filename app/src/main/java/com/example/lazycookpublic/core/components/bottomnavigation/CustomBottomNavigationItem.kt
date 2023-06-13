package com.example.lazycookpublic.core.components.bottomnavigation

import androidx.compose.foundation.layout.*

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun RowScope.BottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selectedContentColor: Color = MaterialTheme.colors.secondary,
    unselectedContentColor: Color = Color.Gray,
): Unit {

    BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Box(
                modifier = modifier

            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "",
                        modifier = modifier.align(Alignment.Center)
                    )
                }
            }
        },
        enabled = enabled,
        selectedContentColor = selectedContentColor,
        unselectedContentColor = unselectedContentColor,

    )



}