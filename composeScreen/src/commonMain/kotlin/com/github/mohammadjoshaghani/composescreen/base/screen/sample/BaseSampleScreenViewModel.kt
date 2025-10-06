package com.github.mohammadjoshaghani.composescreen.base.screen.sample

import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel


class BaseSampleScreenViewModel : BaseViewModel<
        BaseSampleScreenContract.Event,
        BaseSampleScreenContract.State,
        BaseSampleScreenContract.Effect
        >() {

    override fun setInitialState() = BaseSampleScreenContract.State()
    override fun handleEvents(event: BaseSampleScreenContract.Event) = Unit
}