package com.jorge.paulo.mvi.data.remote.dtos

import com.jorge.paulo.mvi.domain.models.Animal

data class AnimalDto(
    val name: String,
    val location: String,
    val image: String,
) {
    fun toAnimal() = Animal(
        name = name,
        image = image
    )
}
