package com.github.mohammadjoshaghani.composescreen.screen

import cafe.adriel.voyager.core.model.StateScreenModel
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel<
        STATE : ViewState<EVENT>,
        EVENT : ViewEvent,
        EFFECT : ViewSideEffect
        >(initialState: STATE) : StateScreenModel<STATE>(initialState) {
    private val _effect = MutableSharedFlow<EFFECT>(replay = 0, extraBufferCapacity = 1)

    val effect = _effect.asSharedFlow()

    abstract fun handleEvents(event: EVENT)

    fun setEffect(builder: () -> EFFECT) {
        _effect.tryEmit(builder())
    }
}