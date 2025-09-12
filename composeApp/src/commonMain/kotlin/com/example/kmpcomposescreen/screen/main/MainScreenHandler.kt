package com.example.kmpcomposescreen.screen.main

import com.github.mohammadjoshaghani.composescreen.base.BaseHandler
import com.github.mohammadjoshaghani.composescreen.compose.errorScreen.ErrorScreenMessageModel
import com.github.mohammadjoshaghani.composescreen.compose.toast.ToastMessageModel

class MainScreenHandler : BaseHandler<
        MainScreenViewModel,
        MainScreenContract.Effect,
        MainScreenContract.Event
        > {
    override fun handleEffects(
        effect: MainScreenContract.Effect,
        viewModel: MainScreenViewModel,
    ) {
        when (effect) {
            is MainScreenContract.Effect.ErrorPage -> {
                viewModel.updateState(
                    errorScreen = ErrorScreenMessageModel(effect.message, effect.event),
                )
            }

            MainScreenContract.Effect.Loading -> {
                viewModel.updateState(isLoading = true)
            }

            MainScreenContract.Effect.Nothing -> Unit

            is MainScreenContract.Effect.ShowToast -> {
                viewModel.updateState(toastMessage = effect.message)
            }

        }
    }

    override fun MainScreenViewModel.updateState(
        isLoading: Boolean,
        toastMessage: ToastMessageModel?,
        errorScreen: ErrorScreenMessageModel<MainScreenContract.Event>?,
    ) {
        setState {
            copy(
                isLoading = isLoading,
                toastMessage = toastMessage,
                errorScreen = errorScreen
            )
        }
    }
}