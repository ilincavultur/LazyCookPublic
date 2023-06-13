package com.example.lazycookpublic.core.components.topbar

import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp



@Composable
fun CustomTopBar(
    title: String?,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = MaterialTheme.colors.onSecondary,
) {
    TopAppBar(
        title = {
            title?.let {
                Text(
                    text = it,
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 24.sp, color = Color.Gray),
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        backgroundColor = backgroundColor,
        contentColor = contentColor
    )
}

