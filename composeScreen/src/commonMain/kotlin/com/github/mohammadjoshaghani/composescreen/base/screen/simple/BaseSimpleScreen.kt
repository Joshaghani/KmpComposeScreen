package com.github.mohammadjoshaghani.composescreen.base.screen.simple

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.app.RenderDialogs
import com.github.mohammadjoshaghani.composescreen.base.handler.IClearStackScreen
import com.github.mohammadjoshaghani.composescreen.base.handler.IRefreshableScreen
import com.github.mohammadjoshaghani.composescreen.base.handler.IShowStickyHeader
import com.github.mohammadjoshaghani.composescreen.base.navigation.Navigator
import com.github.mohammadjoshaghani.composescreen.base.screen.IRootScreen
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose.ApplyStickyVisibilityBySize
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose.AwareHeaderSpacer
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose.StickyHeaderHost
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose.StickySpacer
import com.github.mohammadjoshaghani.composescreen.base.screen.rootScreen.compose.WithSwipeBackIfNeeded
import com.github.mohammadjoshaghani.composescreen.base.screen.simple.compose.ContentScreen
import com.github.mohammadjoshaghani.composescreen.compose.UIAnimatedVisibility
import com.github.mohammadjoshaghani.composescreen.compose.toast.ToastCreator
import com.github.mohammadjoshaghani.composescreen.compose.toast.ToastMessageModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseSimpleScreen : IRootScreen , CoroutineScope{

    override var isVisibleAnimation = MutableStateFlow(false)

    override var showAnimation: Boolean = true

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override var result: List<Any>? = null

    override fun show(replace: Boolean, animation: Boolean) {
        this.showAnimation = animation

        if (this is IClearStackScreen) {
            Navigator.clear()
        }

        if (replace) {
            Navigator.replace(this)
        } else {
            Navigator.push(this)
        }
    }

    @Composable
    override fun ShowScreenFromApp() {
        UIAnimatedVisibility {
            WithSwipeBackIfNeeded(this) {
                StickyHeaderHost(
                    screen = this,
                    state = stickyState
                ) {
                    InitBaseComposeScreen()
                }
            }

            RenderDialogs()

            // اگر این صفحه Sticky دارد، گیت سایز را اعمال کن
            (this as? IShowStickyHeader)?.let { sticky ->
                ApplyStickyVisibilityBySize(
                    screen = sticky
                ) { visible ->
                    stickyState.hasStickyHeader.value = visible
                    if (!visible) stickyState.stickyHeaderHeight = 0.dp
                }
            }
        }
    }


    @Composable
    private fun InitBaseComposeScreen() {
        if (this is IRefreshableScreen) {
            throw IllegalStateException(
                "This screen must not implement IRefreshableScreen. " +
                        "Use BaseScreenLazyList or BaseScreen if you need pull-to-refresh support."
            )
        }

        ContentScreen()
    }


    // API عمومی برای فاصله‌ها:
    @Composable
    fun ExpandedUI(compactUI: @Composable () -> Unit) {
        Row(Modifier.fillMaxSize()) {
            Column {
                StickySpacer(stickyState)
                AwareHeaderSpacer(showAwareHeader.value, heightAwareFaideHeader.value)
                StartedExpandedUI()
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) { compactUI() }
            Column {
                StickySpacer(stickyState)
                AwareHeaderSpacer(showAwareHeader.value, heightAwareFaideHeader.value)
                EndedExpandedUI()
            }
        }
    }


    @Composable
    open fun StartedExpandedUI() {
    }

    @Composable
    open fun EndedExpandedUI() {
    }


    @Composable
    abstract fun ComposeView()

    fun showToast(message: ToastMessageModel){
        launch {
            ToastCreator.showToast(message)
        }
    }

    fun cancelCoroutine() {
        job.cancel()
    }

}