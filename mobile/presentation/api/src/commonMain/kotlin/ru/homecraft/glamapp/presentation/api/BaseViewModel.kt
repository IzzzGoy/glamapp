package ru.homecraft.glamapp.presentation.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S, A, E>(initial: S) : ViewModel() {
    private val innerState = MutableStateFlow<S>(initial)
    val state = innerState.asStateFlow()

    private val innerEffects = Channel<E>()
    val effects = innerEffects.consumeAsFlow()

    protected fun effect(effect: E) {
        viewModelScope.launch {
            innerEffects.send(effect)
        }
    }

    abstract fun dispatch(action: A)

}