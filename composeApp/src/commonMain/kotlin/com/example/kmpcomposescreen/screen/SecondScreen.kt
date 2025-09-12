package com.example.kmpcomposescreen.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Laptop
import androidx.compose.material.icons.twotone.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.kmpcomposescreen.screen.main.MainScreenContract
import com.example.kmpcomposescreen.screen.main.MainScreenHandler
import com.example.kmpcomposescreen.screen.main.MainScreenViewModel
import com.example.kmpcomposescreen.theme.color.colorTheme
import com.github.mohammadjoshaghani.composescreen.base.handler.IIdentifiable
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowBottombar
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowScrollAwareFadingHeader
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbar
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.compose.clickableIcon.IClickableIconModel
import com.github.mohammadjoshaghani.composescreen.compose.dialog.UIAlertDialog

class SecondScreen :
    BaseScreenLazyList<
            MainScreenContract.State,
            MainScreenContract.Event,
            MainScreenContract.Effect,
            MainScreenViewModel>(),
    IShowTopbar,
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

            TextField("" , {})

            repeat(50) {
                Text("Compose View Second $it", color = colorTheme.onBackground)
            }
        }

    }

    override fun titleTopBar(): IShowTopbar.UiTitle {
        return IShowTopbar.UiTitle.TextResult("گوشی اپل iPhone 16 CH رجیستر‌شده دو سیم‌کارت 128 گیگابایت با رم 8 گیگابایت")
    }

//    @OptIn(ExperimentalMaterial3Api::class)
//    @Composable
//    override fun customTopbarUI(scrollBehavior: TopAppBarScrollBehavior): @Composable (() -> Unit)? {
//        return {
//            Text("Welocme To Custom UI :)")
//        }
//    }


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

//    override fun menuIconTopBar(): IClickableIconModel? {
//        return null
//    }

    @Composable
    override fun UIScrollAwareFadingHeader(modifier: Modifier) {
        Column(modifier = modifier) {
            Text("asdflkajsd ;lksjl;ksajd f;lksadjf ;lsakdj ;slkd")
        }
    }

    @Composable
    override fun BottomBarView() {


        val dialog = UIAlertDialog()
            .setTitle("Test Title")
            .setMessage("Test Message")
            .setButtonAction("Close")

        Button({
            dialog.show()
        }) {
            Text("Click Me!")
        }

    }

    override fun getItemsList(state: MainScreenContract.State): MutableList<IIdentifiable> {
        return mutableListOf()
    }

    @Composable
    override fun ItemUI(index: Int, item: Any) {

    }

    override fun actionIconsTopBar(): List<IClickableIconModel> {
        return listOfNotNull(
            IClickableIconModel.ClickableIconVectorModel(
                badgeCount = 3,
                iconId = Icons.TwoTone.ShoppingCart,
                onIconPressed = {
                }),
            IClickableIconModel.ClickableIconVectorModel(
                badgeCount = 2,
                iconId = Icons.TwoTone.Laptop,
                onIconPressed = {
                })

        )
    }


}




