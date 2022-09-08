package com.jorge.paulo.mvi.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorge.paulo.mvi.domain.models.Animal
import com.jorge.paulo.mvi.domain.use_case.GetAnimalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetAnimalUseCase
) : ViewModel() {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    var state = mutableStateOf<MainState>(MainState.Idle)
        private set

    init {
        handlerIntent()
    }

    private fun handlerIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect { collect ->
                when (collect) {
                    is MainIntent.FetchAnimals -> fetchAnimals()
                }
            }
        }
    }

    private fun fetchAnimals() {
        viewModelScope.launch {
            state.value = MainState.Loading
            state.value = try {
                MainState.Animals(
                    useCase()
                )
            } catch (e: Exception) {
                MainState.Error(e.message)
            }
        }
    }

}