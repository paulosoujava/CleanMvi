package com.jorge.paulo.mvi.domain.use_case

import com.jorge.paulo.mvi.domain.repository.MyRepository

data class GetAnimalUseCase(
    private val repository: MyRepository
) {
    suspend operator fun invoke() = repository.doNetWorkCall()

}