package com.example.kmpcomposescreen.screen.main

import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.compose.errorScreen.ErrorScreenMessageModel
import com.github.mohammadjoshaghani.composescreen.compose.toast.ToastMessageModel


class MainScreenContract {

    sealed class Event : ViewEvent {
        data object GetData: Event()
        data object Login: Event()
    }

    data class State(
        override var errorScreen: ErrorScreenMessageModel<Event>? = null,
        override var isLoading: Boolean = false,
        override var toastMessage: ToastMessageModel? = null,
    ) : ViewState<Event>

    sealed class Effect : ViewSideEffect {
        data object Nothing : Effect()
        data object Loading : Effect()
        data class ShowToast(val message: ToastMessageModel) : Effect()
        data class ErrorPage(val message: String, val event: Event) : Effect()
    }
}