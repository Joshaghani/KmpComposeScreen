package com.example.kmpcomposescreen.screen.content.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.github.mohammadjoshaghani.composescreen.screen.BaseSimpleScreen
import com.github.mohammadjoshaghani.composescreen.screen.base.IClearStack
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.contetn.ColumnContent
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.footer.FooterModel
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarTypeTitle
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
        ColumnContent(
//            columns = GridCells.Fixed(1),
            scrollBehavior = scrollBehavior,
            topbarTypeTitle = titleTopBar(scrollBehavior),
            topbarActions = actionIconsTopBar(),
            topbarSticky = {
//                Column(Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)) {
                Text(
                    text = "هدر چسبان (ادامه‌ی تاپ‌بار)",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
//                    }

            },
            startPanel = {
                Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.error)) {
                    Text("START PANEL****")

                }
            },
            endPanel = {
                Text("END PANEL****")
            },

            footerModel = FooterModel(56.dp) {
                Text("END FOTERRRRRRRRRRRRRRRR****")

            }


        ) {


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
    fun titleTopBar(scrollBehavior: TopAppBarScrollBehavior) = TopbarTypeTitle.Compose {
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

//
//    @Composable
//    override fun ComposeView() {
//
//        val listState = rememberLazyListState()
//
//        val headerH = 56.dp
//        val density = LocalDensity.current
//        val headerHPx = with(density) { headerH.toPx() }
//
//        val progress by remember {
//            derivedStateOf {
//                val info = listState.layoutInfo
//                val total = info.totalItemsCount
//                if (total == 0) return@derivedStateOf 0f
//
//                val last = info.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf 0f
//
//                // فقط وقتی آیتم آخر وارد viewport شد شروع کن
//                if (last.index != total - 1) return@derivedStateOf 0f
//
//                // باقی مانده تا رسیدن دقیق به انتهای viewport
//                val remainingPx = (last.offset + last.size - info.viewportEndOffset).coerceAtLeast(0)
//
//                // 0..1
//                (1f - (remainingPx / headerHPx)).coerceIn(0f, 1f)
//            }
//        }
//
//        Box(Modifier.fillMaxSize()) {
//
//            LazyColumn(
//                state = listState,
//                modifier = Modifier.fillMaxSize(),
//                contentPadding = PaddingValues(bottom = headerH) // فضا رزرو می‌کنیم
//            ) {
//                items(20) { Text("Item $it", Modifier.padding(16.dp)) }
//            }
//
//            // هدر ثابت، فقط با offset حرکت می‌کند (بدون تغییر layout)
//            Box(
//                modifier = Modifier
//                    .align(Alignment.BottomCenter)
//                    .height(headerH)
//                    .fillMaxWidth()
//                    .offset {
//                        val y = ((1f - progress) * headerHPx).toInt()
//                        IntOffset(0, y) // 0 یعنی کامل دیده شده، headerHPx یعنی کامل مخفی
//                    }
//            ) {
//                Text("Header", Modifier.align(Alignment.Center))
//            }
//        }
//    }

    data class CategoryModel(
        val title: String,
    )

}






































