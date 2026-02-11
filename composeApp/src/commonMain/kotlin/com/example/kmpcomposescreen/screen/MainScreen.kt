package com.example.kmpcomposescreen.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Start
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.screenModelScope
import com.github.mohammadjoshaghani.composescreen.component.image.IconSourceType
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.IconButtonModel
import com.github.mohammadjoshaghani.composescreen.screen.BaseHandler
import com.github.mohammadjoshaghani.composescreen.screen.BaseScreen
import com.github.mohammadjoshaghani.composescreen.screen.BaseSimpleScreen
import com.github.mohammadjoshaghani.composescreen.screen.BaseViewModel
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.screen.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.screen.errorScreen.ErrorScreenMessageModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose.ColumnContent
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose.ListContent
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.fab.FabIconModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarModel
import com.github.mohammadjoshaghani.composescreen.screen.toast.ToastMessageModel
import com.github.mohammadjoshaghani.composescreen.utils.ScreenTransitionType
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

class MainScreen : BaseSimpleScreen() {
    @Composable
    override fun ComposeView() {
        ColumnContent(
            topbarModel = TopbarModel.Text("Main Screen"),
            bottomBar = {

            },
            navigationIcon = IconButtonModel(
                icon = IconSourceType.IconVector(Icons.Default.Start),
                onClick = {
                    SettingScreen().show()
                },
            ),
            actions = listOf(
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    onClick = {
                        LoginScreen().show(animation = ScreenTransitionType.SCALE)
                    },
                ),
                
            ),
        ) { scrollState ->

            Column(Modifier.verticalScroll(scrollState)) {
                repeat(30) {
                    Text("$it sdfasd jasldkjflksd as djsa;ldkj")
                }
            }

        }
    }
}


class LoginScreen : BaseSimpleScreen() {
    @Composable
    override fun ComposeView() {
        ListContent(
            topbarModel = TopbarModel.Compose {
                Column {
                    Text("TopBar")
                    Text("HEADER")
                }
            },
            isLoading = true,
            onLoadMore = {}
        ) {

            stickyHeader {
                Column {

                }
            }
            items(100) {
                Text("asdlfkla;skasdlfkjasdlfkasjdflkasjdflaksdjfdfj")
            }

            item { }
        }
    }

}


class SettingScreenContract {

    sealed interface Event : ViewEvent {
        data object TEST : Event
    }

    data class State(
        override var errorScreen: ErrorScreenMessageModel<Event>? = null,
        override var isLoading: Boolean = false,
        override var toastMessage: ToastMessageModel? = null
    ) : ViewState<Event>

    sealed interface Effect : ViewSideEffect
}


@Factory
class SettingScreenViewModel : BaseViewModel<
        SettingScreenContract.State,
        SettingScreenContract.Event,
        SettingScreenContract.Effect,
        >(SettingScreenContract.State()) {
    override fun handleEvents(event: SettingScreenContract.Event) {
        screenModelScope.launch {
        }
    }

}

class SettingScreenHandler : BaseHandler<
        SettingScreenContract.Event,
        SettingScreenContract.Effect,
        SettingScreenViewModel,
        > {
    override fun handleEffects(
        effect: SettingScreenContract.Effect,
        viewModel: SettingScreenViewModel
    ) {

    }

    override fun SettingScreenViewModel.updateState(
        isLoading: Boolean,
        toastMessage: ToastMessageModel?,
        errorScreen: ErrorScreenMessageModel<SettingScreenContract.Event>?
    ) {

    }

}

class SettingScreen : BaseScreen<
        SettingScreenContract.State,
        SettingScreenContract.Event,
        SettingScreenContract.Effect,
        SettingScreenViewModel,
        >() {

    @Composable
    override fun getViewModel(): SettingScreenViewModel {
        return SettingScreenViewModel()
    }

    override val handler: SettingScreenHandler = SettingScreenHandler()

    @Composable
    override fun ComposeView(state: SettingScreenContract.State) {
        ColumnContent(

            floatingActionButton = FabIconModel(
                iconVector = Icons.Default.Add,
                onFabPressed = {},
                fabPosition = FabPosition.Start,
//                paddingFromStart = 100.dp
            ),
            startPanel = {
                Text("START PANEL")
            },
            navItems = listOf(
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "testtesttesttesttesttesttesttest",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test2",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                ),
                IconButtonModel(
                    icon = IconSourceType.IconVector(Icons.Default.Memory),
                    title = "test3",
                    isSelected = false,
                    onClick = {}
                )
            )
        ) {
            Column(Modifier.verticalScroll(it)) {
                repeat(100) {
                    Text("asdklfjnasl,df")
                }
            }
        }
    }


}






