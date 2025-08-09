package com.example.kmpcomposescreen.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.kmpcomposescreen.screen.main.MainScreenContract
import com.example.kmpcomposescreen.screen.main.MainScreenHandler
import com.example.kmpcomposescreen.screen.main.MainScreenViewModel
import com.example.kmpcomposescreen.theme.color.colorTheme
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbar
import com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen.BaseScreen

class SecondScreen :
    BaseScreen<
            MainScreenContract.State,
            MainScreenContract.Event,
            MainScreenContract.Effect,
            MainScreenViewModel>(),
    IShowTopbar {

    override val viewModel: MainScreenViewModel = MainScreenViewModel()

    override val handler: MainScreenHandler = MainScreenHandler()

    @Composable
    override fun ComposeView(state: MainScreenContract.State) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(50) {
                Text("Compose View Second", color = colorTheme.onBackground)
            }
        }

    }

    override fun titleTopBar(): String {
        return "Second"
    }


}




