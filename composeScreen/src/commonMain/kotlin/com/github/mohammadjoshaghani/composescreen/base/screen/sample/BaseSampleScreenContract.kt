package com.github.mohammadjoshaghani.composescreen.base.screen.sample

import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.compose.errorScreen.ErrorScreenMessageModel
import com.github.mohammadjoshaghani.composescreen.compose.toast.ToastMessageModel

class BaseSampleScreenContract {
    sealed interface Event : ViewEvent

    data class State(
        override var isLoading: Boolean = false,
        override var errorScreen: ErrorScreenMessageModel<Event>? = null,
        override var toastMessage: ToastMessageModel? = null,
    ) : ViewState<Event>

    sealed interface Effect : ViewSideEffect
}