package com.example.lazycookpublic.feature_recipe.domain.model.step

import com.example.lazycookpublic.feature_recipe.data.local.entity.StepEntity

data class Step(
    val equipment: List<Equipment>,
    val length: String,
    val number: Int,
    val stepDescription: String // description
)

fun Step.toStepEntity() : StepEntity {
    return StepEntity(
        stepDescription = stepDescription
    )
}