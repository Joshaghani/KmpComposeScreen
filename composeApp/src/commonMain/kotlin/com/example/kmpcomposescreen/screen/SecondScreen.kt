package com.example.kmpcomposescreen.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.kmpcomposescreen.screen.main.MainScreenContract
import com.example.kmpcomposescreen.screen.main.MainScreenHandler
import com.example.kmpcomposescreen.screen.main.MainScreenViewModel
import com.example.kmpcomposescreen.theme.color.colorTheme
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowNavigationSideBar
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowScrollAwareFadingHeader
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbarMain
import com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen.BaseScreen
import com.github.mohammadjoshaghani.composescreen.commonCompose.clickableIcon.IClickableIconModel
import com.github.mohammadjoshaghani.composescreen.commonCompose.navigationRail.NavigationItem
import com.github.mohammadjoshaghani.composescreen.commonCompose.navigationRail.NavigationSideBar
import kmpcomposescreen.composeapp.generated.resources.Res
import kmpcomposescreen.composeapp.generated.resources.compose_multiplatform

class SecondScreen :
    BaseScreen<
            MainScreenContract.State,
            MainScreenContract.Event,
            MainScreenContract.Effect,
            MainScreenViewModel>(),
    IShowTopbarMain,
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

}




