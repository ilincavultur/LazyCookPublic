package com.example.lazycookpublic.feature_recipe.data.remote.dto.step

import com.example.lazycookpublic.feature_recipe.domain.model.step.Step

data class StepDto(
    val equipment: List<EquipmentDto>,
    val ingredients: List<IngredientDto>,
    val length: LengthDto,
    val number: Int,
    val step: String // description
)

fun StepDto.toStep() : Step {
    return Step(
        equipment = equipment.map {
            it.toEquipment()
        },
        length = length?.number?.toString() + " " + length?.unit,
        number = number,
        stepDescription = step
    )
}