package com.github.mohammadjoshaghani.composescreen.base.screen.simple

import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel


class BaseSimpleScreenViewModel : BaseViewModel<
        BaseSimpleScreenContract.Event,
        BaseSimpleScreenContract.State,
        BaseSimpleScreenContract.Effect
        >() {

    override fun setInitialState() = BaseSimpleScreenContract.State()
    override fun handleEvents(event: BaseSimpleScreenContract.Event) = Unit
}