package com.kagemora.mediaflow.core.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Effect, Intent> : ViewModel() {

    private val _state: MutableStateFlow<State> by lazy { MutableStateFlow(createInitialState()) }
    val state: StateFlow<State> = _state.asStateFlow()
    private val _effect: Channel<Effect> = Channel(Channel.BUFFERED)
    val effect: Flow<Effect> = _effect.receiveAsFlow()

    protected abstract fun createInitialState(): State

    protected fun setState(state: State.() -> State) {
        _state.update {
            it.state()
        }
    }

    protected fun sendEffect(effect: () -> Effect) {
        viewModelScope.launch {
            _effect.send(effect())
        }
    }

    abstract fun handleIntent(intent: Intent)
}