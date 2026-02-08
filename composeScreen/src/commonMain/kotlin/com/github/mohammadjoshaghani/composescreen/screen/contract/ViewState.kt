package com.github.mohammadjoshaghani.composescreen.screen.contract

import com.github.mohammadjoshaghani.composescreen.screen.errorScreen.ErrorScreenMessageModel
import com.github.mohammadjoshaghani.composescreen.screen.toast.ToastMessageModel


interface ViewState<Event : ViewEvent> {
    var errorScreen: ErrorScreenMessageModel<Event>?
    var isLoading: Boolean
    var toastMessage: ToastMessageModel?
}