package com.github.mohammadjoshaghani.composescreen.base.screen.simple

import com.github.mohammadjoshaghani.composescreen.base.screen.baseUnScrollable.BaseScreenUnScrollable

abstract class BaseSimpleScreen : BaseScreenUnScrollable<
        BaseSimpleScreenContract.State,
        BaseSimpleScreenContract.Event,
        BaseSimpleScreenContract.Effect,
        BaseSimpleScreenViewModel
        >() {

    override val viewModel: BaseSimpleScreenViewModel = BaseSimpleScreenViewModel()

    override val handler: BaseSimpleScreenHandler = BaseSimpleScreenHandler()

}