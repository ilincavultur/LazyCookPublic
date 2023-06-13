package com.example.lazycookpublic.core.components.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lazycookpublic.feature_recipe.domain.model.recipe.Recipe

@Composable
fun RowScope.RecipeCard(
    recipe: Recipe
) {
    Text(
        text = recipe.title,
        style = TextStyle(
            color = Color.DarkGray
        ),
        modifier = Modifier.weight(0.75f)
    )

    Box(
        modifier = Modifier
            .size(70.dp, 70.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        AsyncImage(
            model = recipe.image,
            contentDescription = "image_preview",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }

}