package com.example.lazycookpublic.feature_recipe.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.lazycookpublic.feature_recipe.domain.model.step.Step

@Entity(
    tableName = "recipe_step_table",
    foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["recipeId"],
            childColumns = ["recipe_step_id_foreign"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class StepEntity(
    @PrimaryKey(autoGenerate = true)
    val stepEntityId: Int? = null,
    @ColumnInfo(name = "recipe_step_id_foreign", index = true)
    var recipeStepIdForeign: Int? = null,
    val stepDescription: String
)

fun StepEntity.toStep() : Step {
    return Step(
        equipment = emptyList(),
        length = "",
        number = 0,
        stepDescription = stepDescription
    )

    /*
    val equipment: List<Equipment>,
    val length: String,
    val number: Int,
    val stepDescription: String // description
     */
}

