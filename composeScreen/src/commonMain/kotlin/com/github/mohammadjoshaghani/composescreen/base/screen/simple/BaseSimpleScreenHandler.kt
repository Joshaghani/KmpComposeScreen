package com.github.mohammadjoshaghani.composescreen.base.screen.simple

import com.github.mohammadjoshaghani.composescreen.base.BaseHandler
import com.github.mohammadjoshaghani.composescreen.compose.errorScreen.ErrorScreenMessageModel
import com.github.mohammadjoshaghani.composescreen.compose.toast.ToastMessageModel

class BaseSimpleScreenHandler : BaseHandler<
        BaseSimpleScreenViewModel,
        BaseSimpleScreenContract.Effect,
        BaseSimpleScreenContract.Event
        > {
    override fun handleEffects(
        effect: BaseSimpleScreenContract.Effect,
        viewModel: BaseSimpleScreenViewModel,
    ) = Unit

    override fun BaseSimpleScreenViewModel.updateState(
        isLoading: Boolean,
        toastMessage: ToastMessageModel?,
        errorScreen: ErrorScreenMessageModel<BaseSimpleScreenContract.Event>?,
    ) = Unit
}