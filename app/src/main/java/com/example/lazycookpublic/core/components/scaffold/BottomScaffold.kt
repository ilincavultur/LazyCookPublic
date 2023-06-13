package com.example.lazycookpublic.core.components.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lazycookpublic.feature_recipe.domain.model.step.StepInfo


@Composable
fun BottomScaffold(
    steps: List<StepInfo>,
    bgColor: Color
) {
    Column(
        modifier = Modifier
            .heightIn(min = 50.dp, max = 500.dp)
            .fillMaxSize()
            .background(bgColor)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(
            modifier = Modifier
                .height(3.dp)
                .width(70.dp)
                .background(Color.White)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Steps",
            color = Color.Gray,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                shadow = Shadow(
                    color = Color.DarkGray
                )
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            steps.forEach { stepInfo ->
                itemsIndexed(stepInfo.steps) { idx, step ->
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = (idx + 1).toString() + ". " + step.stepDescription)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

        }

    }
}