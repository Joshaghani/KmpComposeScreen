package com.example.kmpcomposescreen.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmpcomposescreen.screen.main.MainScreenContract
import com.example.kmpcomposescreen.screen.main.MainScreenHandler
import com.example.kmpcomposescreen.screen.main.MainScreenViewModel
import com.example.kmpcomposescreen.theme.color.colorTheme
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowBottombar
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowScrollAwareFadingHeader
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbarMain
import com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen.BaseScreen
import com.github.mohammadjoshaghani.composescreen.commonCompose.UISpacer
import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.IClickableIconModel
import com.github.mohammadjoshaghani.composescreen.commonCompose.dialog.UIAlertDialog

class SecondScreen :
    BaseScreen<
            MainScreenContract.State,
            MainScreenContract.Event,
            MainScreenContract.Effect,
            MainScreenViewModel>(),
    IShowTopbarMain,
    IShowBottombar,
    IShowScrollAwareFadingHeader {

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
                Text("Compose View Second $it", color = colorTheme.onBackground)
            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun customTopbarUI(scrollBehavior: TopAppBarScrollBehavior): @Composable (() -> Unit)? {
        return {
            Text("Welocme To Custom UI :)")
        }
    }


//    override fun actionIconsSideBar(): List<NavigationItem> {
//        NavigationSideBar.selectedItemIndex = 5
//
//        return listOf(
//            NavigationItem(
//                title = "icon",
//                unselectedIcon = Res.drawable.compose_multiplatform,
//                selectedColor = Color.Red,
//                selectedIcon = Res.drawable.compose_multiplatform,
//                unselectedColor = Color.Blue,
//                hasNews = false,
//                badgeCount = null,
//                onIconClicked = {
//                    ThirdScreen().show()
//                }
//            )
//
//        )
//    }

    override fun menuIconTopBar(): IClickableIconModel? {
        return null
    }

    @Composable
    override fun UIScrollAwareFadingHeader(modifier: Modifier) {
        Column(modifier = modifier) {
            Text("asdflkajsd ;lksjl;ksajd f;lksadjf ;lsakdj ;slkd")
        }
    }

    @Composable
    override fun BottomBarView() {


        val dialog = UIAlertDialog().setCustomContent(
            modifier = Modifier.fillMaxWidth(0.5f),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(Modifier.fillMaxWidth()) {
                UISpacer()
                Text("Test Header")
                UISpacer()
                Text("Test Mesage")

                UISpacer()

                Button({
                    UIAlertDialog.getDialog()?.dismiss()
                }) {
                    Text("Click Me!")
                }

            }
        }

        Button({
            dialog.show()
        }) {
            Text("Click Me!")
        }

    }


}




