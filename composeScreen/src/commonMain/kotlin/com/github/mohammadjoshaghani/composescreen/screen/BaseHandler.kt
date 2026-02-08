package com.github.mohammadjoshaghani.composescreen.screen

import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.screen.errorScreen.ErrorScreenMessageModel
import com.github.mohammadjoshaghani.composescreen.screen.toast.ToastMessageModel
import kotlin.contracts.Effect


interface BaseHandler<Event : ViewEvent, Effect : ViewSideEffect, VM : BaseViewModel<*, *, *>> {
    fun handleEffects(effect: Effect, viewModel: VM)

    fun VM.updateState(
        isLoading: Boolean = false,
        toastMessage: ToastMessageModel? = null,
        errorScreen: ErrorScreenMessageModel<Event>? = null,
    )
}