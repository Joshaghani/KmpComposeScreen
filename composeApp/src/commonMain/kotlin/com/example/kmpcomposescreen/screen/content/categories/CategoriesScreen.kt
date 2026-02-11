package com.example.kmpcomposescreen.screen.content.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.koinScreenModel
import com.example.kmpcomposescreen.screen.content.categories.dialog.DialogAddCategory
import com.example.kmpcomposescreen.screen.content.main.utils.iconsActionTopBar
import com.github.mohammadjoshaghani.composescreen.component.UIFadingHeader
import com.github.mohammadjoshaghani.composescreen.component.UISpacer
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.IconButtonModel
import com.github.mohammadjoshaghani.composescreen.component.image.IconSourceType
import com.github.mohammadjoshaghani.composescreen.screen.BaseScreen
import com.github.mohammadjoshaghani.composescreen.screen.BaseSimpleScreen
import com.github.mohammadjoshaghani.composescreen.screen.base.IClearStack
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose.ListContent
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarModel
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CategoriesScreen :
    BaseSimpleScreen(),
    KoinComponent,
    IClearStack {


    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    override fun ComposeView() {

        val windowSizeClass = calculateWindowSizeClass()

//        LaunchedEffect(windowSizeClass) {
//            onEventSent(CategoriesScreenContract.Event.SetScreenSize(windowSizeClass.widthSizeClass))
//        }

        ListContent(
            topbarModel = titleTopBar(),
            actions = iconsActionTopBar(
                IconButtonModel(
                icon = IconSourceType.IconVector(Icons.Rounded.Add),
                title = "addCategory",
                onClick = {
                    DialogAddCategory {}.show()
                }
            )
            )


        ) { listState ->

            stickyHeader {
                UIFadingHeader(listState, 56.dp) {
                    FadingHeader()
                }
            }

            itemsIndexed(getItemsList()) { index, item ->
                ItemUI( index, item)
            }
        }
    }


    fun titleTopBar() = TopbarModel.Compose {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ComposeStickyView()

        }
    }


    @Composable
    fun ComposeStickyView(modifier: Modifier = Modifier) {

        Row(
            modifier = modifier
                .height(80.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var text by remember { mutableStateOf("") }


        }
    }

    @Composable
    fun FadingHeader(modifier: Modifier = Modifier) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(ApplicationConfig.color.surfaceVariant)
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





