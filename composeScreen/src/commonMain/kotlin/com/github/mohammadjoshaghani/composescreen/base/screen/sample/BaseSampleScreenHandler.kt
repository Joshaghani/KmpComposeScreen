package com.github.mohammadjoshaghani.composescreen.base.screen.sample

import com.github.mohammadjoshaghani.composescreen.base.BaseHandler
import com.github.mohammadjoshaghani.composescreen.compose.errorScreen.ErrorScreenMessageModel
import com.github.mohammadjoshaghani.composescreen.compose.toast.ToastMessageModel

class BaseSampleScreenHandler : BaseHandler<
        BaseSampleScreenViewModel,
        BaseSampleScreenContract.Effect,
        BaseSampleScreenContract.Event
        > {
    override fun handleEffects(
        effect: BaseSampleScreenContract.Effect,
        viewModel: BaseSampleScreenViewModel,
    ) = Unit

    override fun BaseSampleScreenViewModel.updateState(
        isLoading: Boolean,
        toastMessage: ToastMessageModel?,
        errorScreen: ErrorScreenMessageModel<BaseSampleScreenContract.Event>?,
    ) = Unit
}