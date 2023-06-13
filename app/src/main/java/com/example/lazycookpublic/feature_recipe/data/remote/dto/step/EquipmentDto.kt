package com.example.lazycookpublic.feature_recipe.data.remote.dto.step

import com.example.lazycookpublic.feature_recipe.domain.model.step.Equipment

data class EquipmentDto(
    val id: Int,
    val image: String,
    val name: String,
    val temperature: TemperatureDto
)

fun EquipmentDto.toEquipment() : Equipment {
    return Equipment(
        equipmentId = id,
        equipmentImage = image,
        equipmentName = name,
        equipmentTemperature = temperature?.number?.toString() + " " + temperature?.unit
    )
}