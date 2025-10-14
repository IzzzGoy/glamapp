package ru.homecraft.glamapp.presentation.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S, A, E>(initial: S) : ViewModel() {
    private val innerState: MutableStateFlow<S> = MutableStateFlow<S>(initial)
    val state: StateFlow<S> = innerState.asStateFlow()

    private val innerEffects: Channel<E> = Channel<E>()
    val effects: Flow<E> = innerEffects.consumeAsFlow()

    protected fun effect(effect: E) {
        viewModelScope.launch {
            innerEffects.send(effect)
        }
    }

    protected fun update(new: S) {
        innerState.value = new
    }

    abstract fun dispatch(action: A)

}