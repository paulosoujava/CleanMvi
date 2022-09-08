package com.jorge.paulo.mvi.data.repository


import com.jorge.paulo.mvi.data.remote.MyApi
import com.jorge.paulo.mvi.domain.models.Animal
import com.jorge.paulo.mvi.domain.repository.MyRepository

class MyRepositoryImpl(
    private val api: MyApi
): MyRepository {
    override suspend fun doNetWorkCall():List<Animal> {
        return api.getAnimals().map { it.toAnimal() }
    }
}