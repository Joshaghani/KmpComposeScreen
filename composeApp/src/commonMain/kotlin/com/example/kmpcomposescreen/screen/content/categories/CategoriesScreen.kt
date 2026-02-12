package com.example.kmpcomposescreen.screen.content.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.Login
import androidx.compose.material.icons.twotone.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.component.UISpacer
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.BadgeItem
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonType
import com.github.mohammadjoshaghani.composescreen.component.itemFadingHeader
import com.github.mohammadjoshaghani.composescreen.screen.BaseSimpleScreen
import com.github.mohammadjoshaghani.composescreen.screen.base.IClearStack
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose.ListContent
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarTypeCompose
import com.github.mohammadjoshaghani.composescreen.utils.AppBarSetting
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import org.koin.core.component.KoinComponent

class CategoriesScreen :
    BaseSimpleScreen(),
    KoinComponent,
    IClearStack {


    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun ComposeView() {

        val windowSizeClass = calculateWindowSizeClass()

//        LaunchedEffect(windowSizeClass) {
//            onEventSent(CategoriesScreenContract.Event.SetScreenSize(windowSizeClass.widthSizeClass))
//        }

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        ListContent(
            topbarTypeCompose = titleTopBar(scrollBehavior),
            scrollBehavior = scrollBehavior,
            appBarSetting = AppBarSetting()
                .setNavRailSetting(
                    color = MaterialTheme.colorScheme.surface,
                    backGroundColor = MaterialTheme.colorScheme.background
                )
                .setBottomBarSetting(
                    containerColor = MaterialTheme.colorScheme.error,
                    scrolledContainerColor = MaterialTheme.colorScheme.onSurface
                )
                .setTopBarSetting(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background,
                ),
            actions = actionIconsTopBar(),
            stickyTopbar = {
                Text(
                    text = "هدر چسبان (ادامه‌ی تاپ‌بار)",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
//            bottomBar = {
//                Column(
//                    Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text("Bottom Bar")
//
//                }
//            }

        ) {

            itemFadingHeader(appBarState = scrollBehavior.state, 56.dp) {
                FadingHeader()
            }

            itemsIndexed(getItemsList()) { index, item ->
                ItemUI(index, item)
            }
        }
    }

    fun actionIconsTopBar(): List<ButtonModel> {


        return listOfNotNull(

            ButtonModel(
                icon = Icons.AutoMirrored.TwoTone.Login,
                title = "ورود|ثبت نام",
                buttonType = ButtonType.TextButton,
                onClick = {
//                LoginScreen().show()
                }
            ),
            ButtonModel(
                badgeItem = BadgeItem.BadgeWithNumber(4),
                icon = Icons.TwoTone.ShoppingCart,
                onClick = {
                }),

            )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun titleTopBar(scrollBehavior: TopAppBarScrollBehavior) = TopbarTypeCompose.Compose {
        Column {
            Text("Medium Top App Bar")
        }
    }


    @Composable
    fun FadingHeader(modifier: Modifier = Modifier) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text("ردیف", Modifier.width(80.dp))
                UISpacer()
                Text("عنوان")
            }

            Spacer(Modifier.weight(1f))

        }
    }

    fun getItemsList(): MutableList<CategoryModel> = mutableListOf(
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
    )


    @Composable
    fun ItemUI(
        index: Int,
        item: Any,
    ) {
        (item as CategoryModel).apply {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (index % 2 == 0) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text("${index + 1}", Modifier.width(80.dp))
                    UISpacer()
                    Text(title)
                }

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.End
                ) {


                }
            }
        }

    }

    data class CategoryModel(
        val title: String,
    )

}





