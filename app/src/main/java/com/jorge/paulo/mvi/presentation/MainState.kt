package com.jorge.paulo.mvi.presentation

import com.jorge.paulo.mvi.domain.models.Animal

sealed class MainState{
    object Idle: MainState()
    object Loading: MainState()
    data class Animals(val animal: List<Animal>): MainState()
    data class Error(val error: String?): MainState()
}

sealed class MainIntent{
    object FetchAnimals: MainIntent()
}
