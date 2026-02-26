package com.example.kmpcomposescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmpcomposescreen.screen.content.main.MainScreen
import com.example.kmpcomposescreen.theme.ExampleTheme
import com.example.kmpcomposescreen.theme.color.getApplicationColorScheme
import com.github.mohammadjoshaghani.composescreen.app.ComposeScreen
import com.github.mohammadjoshaghani.composescreen.component.button.UIPrimaryButton
import com.github.mohammadjoshaghani.composescreen.extension.themeClickable
import com.github.mohammadjoshaghani.composescreen.screen.BaseHandler
import com.github.mohammadjoshaghani.composescreen.screen.BaseScreen
import com.github.mohammadjoshaghani.composescreen.screen.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.screen.errorScreen.ErrorScreenMessageModel
import com.github.mohammadjoshaghani.composescreen.screen.toast.ToastMessageModel

@Composable
fun App() {
    ExampleTheme {
        ComposeScreen(
            listOf(TestScreen()),
            color = it,

            errorScreen = { message, retryClick ->
                Text(
                    message,
                    modifier = Modifier.themeClickable { retryClick() })
            }
        )
    }
}

class TestContract {

    sealed interface Event : ViewEvent {
        data object TestEvent : Event
    }

    data class State(
        override var errorScreen: ErrorScreenMessageModel<Event>? = null,
        override var isLoading: Boolean = false,
        override var toastMessage: ToastMessageModel? = null,
    ) : ViewState<Event>

    sealed interface Effect : ViewSideEffect {
        data object Loading : Effect
    }


}


class TestViewModel : BaseViewModel<
        TestContract.State,
        TestContract.Event,
        TestContract.Effect,
        >(TestContract.State()) {

    override fun handleEvents(event: TestContract.Event) {
        setEffect { TestContract.Effect.Loading }
    }

}

class TestHandler : BaseHandler<
        TestContract.Event,
        TestContract.Effect,
        TestViewModel,
        > {
    override fun handleEffects(
        effect: TestContract.Effect,
        viewModel: TestViewModel
    ) {
        viewModel.setState { copy(isLoading = true) }
    }

    override fun TestViewModel.updateState(
        isLoading: Boolean,
        toastMessage: ToastMessageModel?,
        errorScreen: ErrorScreenMessageModel<TestContract.Event>?
    ) {

    }

}

class TestScreen : BaseScreen<
        TestContract.State,
        TestContract.Event,
        TestContract.Effect,
        TestViewModel,
        >() {
    @Composable
    override fun getViewModel(): TestViewModel {
        return TestViewModel()
    }

    override val handler: TestHandler = TestHandler()

    @Composable
    override fun ComposeView(state: TestContract.State) {
        UIPrimaryButton("Clock") {
            onEventSent(TestContract.Event.TestEvent)
        }
    }

}