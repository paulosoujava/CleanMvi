package com.jorge.paulo.mvi.domain.repository


import com.jorge.paulo.mvi.domain.models.Animal

interface MyRepositoryLocal {

    suspend  fun getAll(): List<Animal>
    suspend  fun loadAllByIds(animalId: IntArray): List<Animal>
    suspend  fun findByName(name: String ): Animal
    suspend  fun insert( animal: Animal)
    suspend  fun delete(animal: Animal)
}