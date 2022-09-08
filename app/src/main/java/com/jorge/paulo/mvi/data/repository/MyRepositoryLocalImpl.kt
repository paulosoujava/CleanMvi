package com.jorge.paulo.mvi.data.repository


import com.jorge.paulo.mvi.data.local.AnimalDao
import com.jorge.paulo.mvi.data.local.entity.AnimalEntity
import com.jorge.paulo.mvi.data.remote.MyApi
import com.jorge.paulo.mvi.domain.models.Animal
import com.jorge.paulo.mvi.domain.repository.MyRepositoryApi
import com.jorge.paulo.mvi.domain.repository.MyRepositoryLocal

class MyRepositoryLocalImpl(
    private val dao: AnimalDao
): MyRepositoryLocal {
     override suspend fun getAll(): List<Animal> {
        return dao.getAll().map { it.toAnimal() }
    }

    override suspend fun loadAllByIds(animalId: IntArray): List<Animal> {
        return dao.loadAllByIds(animalId).map { it.toAnimal() }
    }

    override suspend fun findByName(name: String): Animal {
        return dao.findByName(name).toAnimal()
    }

    override suspend fun insert( animal: Animal) {
        return dao.insert(AnimalEntity.toEntity(animal))
    }

    override suspend fun delete(animal: Animal) {
        dao.delete(AnimalEntity.toEntity(animal))
    }

}