package com.example.kmpcomposescreen.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Login
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kmpcomposescreen.screen.MainScreen2
import com.example.kmpcomposescreen.theme.color.colorTheme
import com.github.mohammadjoshaghani.composescreen.base.handler.IClearStackScreen
import com.github.mohammadjoshaghani.composescreen.base.handler.IIdentifiable
import com.github.mohammadjoshaghani.composescreen.base.handler.ILazyLoadingList
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowNavigationSideBar
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowScrollAwareFadingHeader
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowTopbarMain
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.compose.component.UIPrimaryButton
import com.github.mohammadjoshaghani.composescreen.compose.component.clickableIcon.IClickableIconModel
import com.github.mohammadjoshaghani.composescreen.compose.dialog.BaseDialog
import com.github.mohammadjoshaghani.composescreen.compose.dialog.UIAlertDialog
import com.github.mohammadjoshaghani.composescreen.compose.navigationRail.NavigationItem
import com.github.mohammadjoshaghani.composescreen.compose.navigationRail.NavigationSideBar
import com.github.mohammadjoshaghani.composescreen.compose.toast.ToastCreator
import com.github.mohammadjoshaghani.composescreen.extension.toast
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import kmpcomposescreen.composeapp.generated.resources.Res
import kmpcomposescreen.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class TestDialg : BaseDialog() {
    @Composable
    override fun ComposeView() {

        UIPrimaryButton("adsfsadf") {
            TestDialg2().show()
        }
    }

}

class TestDialg2 : BaseDialog() {
    @Composable
    override fun ComposeView() {

        Text("asdkfjhaskdjfh klsajd fhlkasdj")
        Text("asdkfjhaskdjfh klsajd fhlkasdj")
        Text("asdkfjhaskdjfh klsajd fhlkasdj")
        Text("asdkfjhaskdjfh klsajd fhlkasdj")
        Text("asdkfjhaskdjfh klsajd fhlkasdj")
        Text("asdkfjhaskdjfh klsajd fhlkasdj")

        UIPrimaryButton("adsfsadf") {
            MainScope().launch {
                ToastCreator.showToast("hellop".toast())
            }
        }
    }

}

class MainScreen :
    BaseScreenLazyList<
            MainScreenContract.State,
            MainScreenContract.Event,
            MainScreenContract.Effect,
            MainScreenViewModel
            >(),
    IShowNavigationSideBar,
    IShowTopbarMain,
    IShowStickyHeader,
    IShowScrollAwareFadingHeader,
    ILazyLoadingList,
    IClearStackScreen {

    override val viewModel = MainScreenViewModel()

    override val handler = MainScreenHandler()


    override fun menuIconTopBar(): IClickableIconModel {
        return IClickableIconModel.ClickableIconModel(
            iconId = Res.drawable.compose_multiplatform,
            tint = ApplicationConfig.config.color.onPrimary,
            onIconPressed = {
                TestDialg()
                    .onDismissRequest {

                    }
                    .show()
            }
        )
    }


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
                    MainScreen2().show()
                }
            )

        )
    }

    override fun actionIconsTopBar(): List<IClickableIconModel> {
        return listOf(
            IClickableIconModel.ClickableIconVectorModel(
                iconId = Icons.AutoMirrored.Rounded.Login,
                tint = ApplicationConfig.config.color.onPrimary,
                onIconPressed = {
                }
            ),
            IClickableIconModel.ClickableIconVectorModel(
                iconId = Icons.AutoMirrored.Rounded.Login,
                title = "ورود | ثبت نام",
                tint = ApplicationConfig.config.color.onPrimary,
                doesButtonHaveBorder = false,
                onIconPressed = {
                    a.show()

//                    UIAlertDialog()
//                        .setMessage("You ar login")
//                        .setButtonAction("login", false) {
//                            onEventSent(MainScreenContract.Event.Login)
//                        }
//                        .show()
                }
            ),

            )
    }

    lateinit var a: UIAlertDialog

    @Composable
    override fun ComposeStickyView(modifier: Modifier) {


        Row(
            modifier = modifier
                .height(80.dp)
                .background(colorTheme.background)
                .fillMaxWidth()
        ) {
            var text by remember { mutableStateOf("") }

            TextField(
                value = text,
                modifier = Modifier
                    .padding(16.dp)
                    .width(500.dp),
                onValueChange = {})

//
//            UIPrimaryButton(
//                modifier = modifier
//                    .width(200.dp)
//                    .padding(horizontal = 16.dp),
//                title = strings.message.addCategory
//            ) {
//
//            }

        }
    }

    @Composable
    override fun UIScrollAwareFadingHeader(modifier: Modifier) {
        Row(
            modifier = modifier
                .height(56.dp)
                .fillMaxWidth()
                .background(ApplicationConfig.config.color.surfaceVariant)
                .padding(8.dp)
        ) {
            TableHeaderCell("Customer", 0.2f)
            TableHeaderCell("Email", 0.25f)
            TableHeaderCell("Phone", 0.15f)
            TableHeaderCell("Location", 0.15f)
            TableHeaderCell("Orders", 0.1f)
            TableHeaderCell("Total Spent", 0.15f)
        }


    }

    fun getItemsList(): MutableList<IIdentifiable> = mutableListOf(
        CategoryModel("موبایل"),
        CategoryModel("لپتاپ"),
        CategoryModel("کالای دیجیتال"),
        CategoryModel("لوازم برقی خانگی"),
        CategoryModel("دوربین"),
        CategoryModel("تلسکوپ"),
        CategoryModel("تلسکوپ"),
        CategoryModel("تلسکوپ"),
        CategoryModel("تلسکوپ"),
        CategoryModel("تلسکوپ"),
        CategoryModel("تلسکوپ"),
        CategoryModel("تلسکوپ"),
        CategoryModel("تلسکوپ"),
        CategoryModel("تلسکوپ"),
        CategoryModel("تلسکوپ"),
        CategoryModel("تلسکوپ"),
        CategoryModel("تلسکوپ"),
        CategoryModel("تلسکوپ"),
        CategoryModel("تلسکوپ"),
        CategoryModel("تلسکوپ"),
    )

    override fun getItemsList(state: MainScreenContract.State): MutableList<IIdentifiable> {
        return getItemsList()
    }

    @Composable
    override fun ItemUI(state: MainScreenContract.State, index: Int, item: Any) {
        (item as CategoryModel).apply {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                TableCell("${title}\n${id}", 0.2f)
                TableCell("email", 0.25f)
                TableCell("phone", 0.15f)
                TableCell("${"location"}\n${"country"}", 0.15f)
                TableCell("orders", 0.1f)
                TableCell("totalSpent", 0.15f)
            }
        }

    }

    override fun lazyColumnScrolledEnd() {
        println("*&&*&*lazyColumnScrolledEnd")
    }


    data class CategoryModel(
        val title: String,
    ) : IIdentifiable

}

@Composable
fun RowScope.TableHeaderCell(text: String, weight: Float) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .weight(weight)
            .padding(4.dp)
    )
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    color: Color = ApplicationConfig.config.color.onBackground,
) {
    Text(
        text = text,
        color = color,
        modifier = Modifier
            .weight(weight)
            .padding(4.dp)
    )
}



