package com.github.mohammadjoshaghani.composescreen.screen.toast


fun String.toast(state: ToastState = ToastState.ERROR): ToastMessageModel {
    return ToastMessageModel(this, state)
}

