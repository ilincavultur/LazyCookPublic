package com.example.lazycookpublic.feature_recipe.data.remote.dto.step

import com.example.lazycookpublic.feature_recipe.domain.model.step.StepInfo

data class StepsResponseDto(
    val name: String,
    val steps: List<StepDto>
)

fun StepsResponseDto.toStepInfo() : StepInfo {
    return StepInfo(
        steps = steps.map {
            it.toStep()
        }
    )
}

