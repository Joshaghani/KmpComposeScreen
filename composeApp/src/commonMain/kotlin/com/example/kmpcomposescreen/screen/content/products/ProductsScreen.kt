package com.example.kmpcomposescreen.screen.content.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmpcomposescreen.screen.content.main.utils.iconsActionTopBar
import com.example.kmpcomposescreen.textFieldTheme.UITextField
import com.github.mohammadjoshaghani.composescreen.component.button.IconButton.ButtonModel
import com.github.mohammadjoshaghani.composescreen.screen.BaseSimpleScreen
import com.github.mohammadjoshaghani.composescreen.screen.base.IClearStack
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose.ListContent
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarTypeCompose
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import org.koin.core.component.KoinComponent

class ProductsScreen : BaseSimpleScreen(),
    KoinComponent, IClearStack {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun ComposeView() {


        ListContent(
            topbarTypeCompose = titleTopBar(),
            actions = iconsActionTopBar(
                ButtonModel(
                    icon =Icons.Rounded.Add,
                    title = "addProduct",
                    onClick = {

                    }
                )
            )
        ) {
            stickyHeader {
                FadingHeader(Modifier)
            }
            itemsIndexed(getItemsList()) { index, item ->
                ItemUI(index, item)
            }
        }
    }

    fun titleTopBar() = TopbarTypeCompose.Compose {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ComposeStickyView(Modifier)
        }
    }


    fun getItemsList(): MutableList<ProductModel> {
        return mutableListOf(
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),
            ProductModel("iPhone 16", "موبایل", "", "82000000000", 10, 60),

            )
    }

    @Composable
    fun ItemUI(index: Int, item: Any) {
        (item as ProductModel).apply {

            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(if (index % 2 == 0) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background)
                    .padding(horizontal = 8.dp), verticalAlignment =
                    if (editItem) Alignment.Top else Alignment.CenterVertically
            ) {


                Column(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .weight(0.2f)
                ) {
                    Text(inventory.toString(), modifier = Modifier.fillMaxWidth())
                    if (editItem) {


                    }
                }


            }

        }
    }


    @Composable
    fun ComposeStickyView(modifier: Modifier) {
        Row(
            modifier = modifier.height(80.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            var text by remember { mutableStateOf("") }

            UITextField(
                modifier = Modifier.padding(16.dp).width(500.dp),
                text = text,
                placeholder = "searchProduct",
                onChangeValue = {
                    text = it
                })


        }
    }

    @Composable
    fun FadingHeader(modifier: Modifier) {
        Row(
            modifier = modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant).padding(8.dp)
        ) {
            Box(
                modifier = Modifier.weight(1f).padding(4.dp)
            )

        }
    }

    data class ProductModel(
        val title: String,
        val category: String,
        val description: String,
        val price: String,
        val minimumCount: Int,
        val inventory: Int,
    ) {

        var editItem by mutableStateOf(false)

    }

}