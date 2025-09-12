package com.example.kmpcomposescreen.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.kmpcomposescreen.screen.main.MainScreen
import com.example.kmpcomposescreen.screen.main.MainScreenContract
import com.example.kmpcomposescreen.screen.main.MainScreenHandler
import com.example.kmpcomposescreen.screen.main.MainScreenViewModel
import com.example.kmpcomposescreen.theme.color.colorTheme
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowNavigationSideBar
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbar
import com.github.mohammadjoshaghani.composescreen.base.screen.baseScreen.BaseScreen
import com.github.mohammadjoshaghani.composescreen.compose.navigationRail.NavigationItem
import com.github.mohammadjoshaghani.composescreen.compose.navigationRail.NavigationSideBar
import kmpcomposescreen.composeapp.generated.resources.Res
import kmpcomposescreen.composeapp.generated.resources.compose_multiplatform

class ThirdScreen :
    BaseScreen<
            MainScreenContract.State,
            MainScreenContract.Event,
            MainScreenContract.Effect,
            MainScreenViewModel>(),
    IShowTopbar,
    IShowNavigationSideBar {

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

    override fun titleTopBar() = IShowTopbar.UiTitle.TextResult("Second")

    override fun actionIconsSideBar(): List<NavigationItem> {
        NavigationSideBar.selectedItemIndex = 5

        return listOf(
            NavigationItem(
                title = "icon",
                unselectedIcon = Res.drawable.compose_multiplatform,
                selectedColor = Color.Red,
                selectedIcon = Res.drawable.compose_multiplatform,
                unselectedColor = Color.Blue,
                hasNews = false,
                badgeCount = null,
                onIconClicked = {
                    MainScreen().show()
                }
            )

        )
    }

}




