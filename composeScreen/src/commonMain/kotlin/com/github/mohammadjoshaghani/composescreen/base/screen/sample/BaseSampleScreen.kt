package com.github.mohammadjoshaghani.composescreen.base.screen.sample

import com.github.mohammadjoshaghani.composescreen.base.screen.baseUnScrollable.BaseScreenUnScrollable

abstract class BaseSampleScreen : BaseScreenUnScrollable<
        BaseSampleScreenContract.State,
        BaseSampleScreenContract.Event,
        BaseSampleScreenContract.Effect,
        BaseSampleScreenViewModel
        >() {

    override val viewModel: BaseSampleScreenViewModel = BaseSampleScreenViewModel()

    override val handler: BaseSampleScreenHandler = BaseSampleScreenHandler()

}