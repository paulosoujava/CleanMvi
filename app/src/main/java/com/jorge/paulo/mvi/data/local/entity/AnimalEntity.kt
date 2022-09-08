package com.jorge.paulo.mvi.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jorge.paulo.mvi.domain.models.Animal


@Entity
data class AnimalEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "image") val image: String? = null
){
    fun toAnimal() = Animal(
        name = name ?: "",
        image = image ?: ""
    )
    companion object{
        fun toEntity(animal: Animal) = AnimalEntity(
            uid = animal.uid,
            name = animal.name,
            image = animal.image
        )
    }


}