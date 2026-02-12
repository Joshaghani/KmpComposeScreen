package com.example.kmpcomposescreen.screen.content.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kmpcomposescreen.screen.content.main.utils.iconsActionTopBar
import com.example.kmpcomposescreen.textFieldTheme.UITextField
import com.github.mohammadjoshaghani.composescreen.component.UIBorderCard
import com.github.mohammadjoshaghani.composescreen.component.UISpacer
import com.github.mohammadjoshaghani.composescreen.component.button.UITextButton
import com.github.mohammadjoshaghani.composescreen.screen.BaseSimpleScreen
import com.github.mohammadjoshaghani.composescreen.screen.base.IClearStack
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.compose.ListContent
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarTypeCompose
import com.github.mohammadjoshaghani.composescreen.utils.ApplicationConfig
import org.koin.core.component.KoinComponent

class OrderScreen : BaseSimpleScreen(),
    KoinComponent,
    IClearStack {


    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun ComposeView() {

        ListContent(
            topbarTypeCompose = titleTopBar(),
            actions = iconsActionTopBar()
        ) {
            stickyHeader {
                ComposeStickyView()
            }

            stickyHeader {
//                UIFadingHeader(listState, 56.dp) {
                    FadingHeader()
//                }
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

        }
    }


    @Composable
    fun ComposeStickyView(modifier: Modifier = Modifier) {

        Row(
            modifier = modifier
                .height(80.dp)
                .background(MaterialTheme.colorScheme.background).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var text by remember { mutableStateOf("") }

            UITextField(
                modifier = Modifier
                    .padding(16.dp)
                    .width(500.dp),
                text = text,
                placeholder = "searchOrder",
                onChangeValue = {
                    text = it
                })

            Spacer(Modifier.weight(1f))

            var selectedFilter by remember { mutableStateOf<Int?>(null) }

            UIBorderCard(
                shape = CircleShape,
                paddingValue = 4.dp,
                clickable = {
                    selectedFilter = if (selectedFilter == 0) null else 0
                },
                backgroundColor = if (selectedFilter == 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                modifier = Modifier
            ) {
                Text(
                    "ثبت شده : ۱۰",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = if (selectedFilter == 0) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                )
            }
            UISpacer()
            UIBorderCard(
                shape = CircleShape,
                paddingValue = 4.dp,
                clickable = {
                    selectedFilter = if (selectedFilter == 1) null else 1
                },
                backgroundColor = if (selectedFilter == 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                modifier = Modifier
            ) {
                Text(
                    "تحویل شده : ۱۰",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = if (selectedFilter == 1) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                )
            }
            UISpacer()
            UIBorderCard(
                shape = CircleShape,
                paddingValue = 4.dp,
                clickable = {
                    selectedFilter = if (selectedFilter == 2) null else 2
                },
                backgroundColor = if (selectedFilter == 2) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                modifier = Modifier
            ) {
                Text(
                    "درانتظار آماده سازی  : ۱۰",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = if (selectedFilter == 2) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                )
            }
            UISpacer()
            UIBorderCard(
                shape = CircleShape,
                paddingValue = 4.dp,
                clickable = {
                    selectedFilter = if (selectedFilter == 3) null else 3
                },
                backgroundColor = if (selectedFilter == 3) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            ) {
                Text(
                    "انجام شده : ۱۰",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = if (selectedFilter == 3) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                )
            }
            UISpacer()
        }
    }

    @Composable
    fun FadingHeader(modifier: Modifier = Modifier) {
        Row(
            modifier = modifier.fillMaxWidth()
                .background(ApplicationConfig.color.surfaceVariant).padding(8.dp)
        ) {

            Text(
                text = "تغییر وضعیت",
                style = MaterialTheme.typography.bodyLarge,
                softWrap = false,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.wrapContentWidth()
            )
        }


    }

    fun getItemsList(): MutableList<OrderModel> = mutableListOf(
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.PAID),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.REFUNDED),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.PROCESSING),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.PAID),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.REFUNDED),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.REFUNDED),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.REFUNDED),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.PENDING),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.PAID),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.REFUNDED),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.REFUNDED),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.PAID),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.PAID),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.PROCESSING),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.REFUNDED),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.PAID),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.REFUNDED),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.PAID),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.PAID),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.REFUNDED),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.PROCESSING),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.REFUNDED),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.PAID),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.REFUNDED),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.REFUNDED),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.PROCESSING),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.PROCESSING),
        OrderModel("محمد", "۱۴۰۴/۵/۲۰", "۱۰۰۰۰۰۰", StatusOrder.PROCESSING),

        )


    @Composable
    fun ItemUI(index: Int, item: Any) {
        (item as OrderModel).apply {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (index % 2 == 0) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {


                UITextButton("تغییر وضعیت") {

                }

            }

        }

    }


    data class OrderModel(
        val customer: String,
        val date: String,
        val price: String,
        val status: StatusOrder,
    )

    enum class StatusOrder(val persianName: String) {
        PAID("پرداخت شده"), PENDING("در انتظار ارسال"), REFUNDED("برگشتی"), PROCESSING("در حال پردازش")
    }

}






