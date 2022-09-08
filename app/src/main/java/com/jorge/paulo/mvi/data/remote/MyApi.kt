package com.jorge.paulo.mvi.data.remote

import com.jorge.paulo.mvi.data.remote.dtos.AnimalDto
import com.jorge.paulo.mvi.domain.models.Animal
import retrofit2.http.GET

interface MyApi {

    @GET("animals.json")
    suspend fun getAnimals(): List<AnimalDto>
}