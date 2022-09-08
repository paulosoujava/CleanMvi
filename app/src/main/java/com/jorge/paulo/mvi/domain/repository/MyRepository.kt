package com.jorge.paulo.mvi.domain.repository

import com.jorge.paulo.mvi.domain.models.Animal

interface MyRepository {
    suspend fun doNetWorkCall():List<Animal>
}