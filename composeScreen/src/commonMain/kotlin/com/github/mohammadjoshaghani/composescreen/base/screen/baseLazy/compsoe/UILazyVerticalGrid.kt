package com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.compsoe

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowFab
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.BaseScreenLazyList
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension.renderEmptyState
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension.renderItemsIndexed
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.extension.renderLoadMore
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils.RunIfShowAwareHeader
import com.github.mohammadjoshaghani.composescreen.base.screen.baseLazy.utils.RunIfShowSticky
import com.github.mohammadjoshaghani.composescreen.commonCompose.UISpacer
import kotlinx.coroutines.delay

@Composable
fun <State : ViewState<Event>, Event : ViewEvent> BaseScreenLazyList<State, *, *, *>.UILazyVerticalGrid(
    state: State,
    modifier: Modifier = Modifier,
) {

    var showItem by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(16) // یک فریم تاخیر
        showItem = true
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(verticalGridMinSize.dp),
        modifier = modifier
    ) {
        if (showItem) {
            item {
                RunIfShowSticky {
                    Spacer(modifier = Modifier.height(stickyState.stickyHeaderHeight))
                }
                RunIfShowAwareHeader {
                    Spacer(Modifier.height(heightAwareFaideHeader.value))
                }
                ComposeView(state)
            }
        }
        renderItemsIndexed(getItemsList(state)) { index, item ->
            ItemUI(index, item)
        }
        renderLoadMore(getItemsList(state), this@UILazyVerticalGrid)
        renderEmptyState(getItemsList(state), this@UILazyVerticalGrid)

        item { FooterUI(state) }
        item { UISpacer(if (this@UILazyVerticalGrid is IShowFab) 150 else 50) }
    }

}