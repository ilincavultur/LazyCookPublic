package com.example.lazycookpublic.core.components.bottomnavigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val route: String,
    val icon: ImageVector? =  null,
    val contentDescription: String? = null,
)