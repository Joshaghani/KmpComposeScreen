package com.example.kmpcomposescreen.screen.main

import com.github.mohammadjoshaghani.composescreen.base.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.compose.toast.ToastState
import com.github.mohammadjoshaghani.composescreen.extension.toast
import kotlinx.coroutines.delay

class MainScreenViewModel(
) : BaseViewModel<
        MainScreenContract.Event,
        MainScreenContract.State,
        MainScreenContract.Effect,
        >() {

    override fun initViewModel() {
        launchOnScope {
            setState {
                copy(isLoading = true)
            }
            delay(100)
            setState {
                copy(isLoading = false)
            }
        }
    }

    override fun setInitialState() = MainScreenContract.State()

    override fun handleEvents(event: MainScreenContract.Event) {
        when (event) {
            MainScreenContract.Event.GetData -> {
                launchOnScope {
                    setEffect {
                        MainScreenContract.Effect.Loading
                    }
                    delay(2000)

                    setEffect {
                        MainScreenContract.Effect.ShowToast("Loading Started".toast(ToastState.SUCCESS))
                    }


                }
            }
        }
    }

}