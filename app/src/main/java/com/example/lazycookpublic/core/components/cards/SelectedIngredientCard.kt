package com.example.lazycookpublic.core.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lazycookpublic.ui.theme.Shapes

@Composable
fun SelectedIngredientCard(
    text: String,
    imageUrl: String
) {
    Card(
        elevation = 10.dp,
        shape = Shapes.medium,
        modifier = Modifier.size(100.dp, 125.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "image_preview",
                modifier = Modifier.size(100.dp, 100.dp)
            )
            Row{
                Text(
                    text = text,
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}