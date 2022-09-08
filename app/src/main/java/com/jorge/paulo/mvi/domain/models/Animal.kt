package com.jorge.paulo.mvi.domain.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


data class Animal(
    val uid: Int = 0,
    val name: String = "",
    val image: String = "",
)