package com.example.kmpcomposescreen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kmpcomposescreen.UIRoundedCard
import com.example.kmpcomposescreen.screen.main.MainScreenContract
import com.example.kmpcomposescreen.screen.main.MainScreenHandler
import com.example.kmpcomposescreen.screen.main.MainScreenViewModel
import com.example.kmpcomposescreen.textFieldTheme.UITextFieldTitle
import com.github.mohammadjoshaghani.composescreen.base.screen.baseUnScrollable.BaseScreenUnScrollable
import com.github.mohammadjoshaghani.composescreen.compose.component.UIPrimaryButton
import com.github.mohammadjoshaghani.composescreen.compose.component.UISpacer
import com.github.mohammadjoshaghani.composescreen.compose.topbar.UITopBar

class MainScreen2 :
    BaseScreenUnScrollable<
            MainScreenContract.State,
            MainScreenContract.Event,
            MainScreenContract.Effect,
            MainScreenViewModel
            >() {

    override val viewModel = MainScreenViewModel()

    override val handler = MainScreenHandler()

    @Composable
    override fun ComposeView(state: MainScreenContract.State) {
        Column(
            Modifier
                .background(Color.Red)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            PortRateScreen()
        }
    }

    @Composable
    fun PortRateScreen() {

        var phoneNumber by remember { mutableStateOf("") }
        val focusRequesterMobile = remember { FocusRequester() }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Text("UIHorizontalLogo()")

            UIRoundedCard {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Strings.Title.enter_mytipax",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                    )
                }

                Text(
                    text = "Strings.Messages.enter_phone_number",
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                )

                UITextFieldTitle(
                    title = "phoneNumber",
                    text = "asdf",
                    onChangeValue = {}
                )

                UISpacer()

                UIPrimaryButton(
                    shape = MaterialTheme.shapes.medium,
                    title = "Strings.Title.sent_confirmation_code",
                    modifier = Modifier.fillMaxWidth()
                ) {
                }
            }
        }
        LaunchedEffect(Unit) {
            focusRequesterMobile.requestFocus()
        }
    }

    @Composable
    override fun BottomBarView() {
//        Column(
//            Modifier
//                .padding(16.dp)
//                .fillMaxWidth()
//                .height(56.dp)
//        ) {
//            TextField(value = "asdfsadfdf", onValueChange = {})
//        }
    }


    override fun titleTopBar() = UITopBar.Text("asdf")


}



