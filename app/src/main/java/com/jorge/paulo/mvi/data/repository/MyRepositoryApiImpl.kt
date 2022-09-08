package com.jorge.paulo.mvi.data.repository


import com.jorge.paulo.mvi.data.remote.MyApi
import com.jorge.paulo.mvi.domain.models.Animal
import com.jorge.paulo.mvi.domain.repository.MyRepositoryApi

class MyRepositoryApiImpl(
    private val api: MyApi
): MyRepositoryApi {
    override suspend fun doNetWorkCall():List<Animal> {
        return api.getAnimals().map { it.toAnimal() }
    }
}