package com.github.mohammadjoshaghani.composescreen.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarType

class AppBarSetting {

    @OptIn(ExperimentalMaterial3Api::class)
    internal var topAppBar: TopAppBar = TopAppBar()

    internal var bottomAppBar: BottomAppBar = BottomAppBar()
    internal var navigationRailAppBar: NavigationRailAppBar = NavigationRailAppBar()


    fun setTopBarSetting(
        containerColor: Color = Color.Unspecified,
        scrolledContainerColor: Color = Color.Unspecified,
        topbarType: TopbarType = TopbarType.NORMAL,
    ) = apply {
        this.topAppBar.apply {
            this.containerColor = containerColor
            this.scrolledContainerColor = scrolledContainerColor
            this.topbarType = topbarType
            this.isStickyHeader = isStickyHeader
        }
    }

    fun setBottomBarSetting(
        containerColor: Color = Color.Unspecified,
        scrolledContainerColor: Color = Color.Unspecified,
        shadowElevation: Dp = 4.dp,
        tonalElevation: Dp = 0.dp,
    ) = apply {
        this.bottomAppBar.apply {
            this.containerColor = containerColor
            this.scrolledContainerColor = scrolledContainerColor
            this.shadowElevation = shadowElevation
            this.tonalElevation = tonalElevation
        }
    }

    fun setNavRailSetting(
        color: Color = Color.Unspecified,
        backGroundColor: Color = ApplicationConfig.color.onSecondary,
        shadowElevation: Dp = 4.dp,
        shape: Shape = RoundedCornerShape(12.dp),
        tonalElevation: Dp = 0.dp,
        padding: Dp = 16.dp
    ) = apply {
        this.navigationRailAppBar.apply {
            this.color = color
            this.backGroundColor = backGroundColor
            this.shadowElevation = shadowElevation
            this.shape = shape
            this.tonalElevation = tonalElevation
            this.padding = padding
        }
    }

}

data class TopAppBar @OptIn(ExperimentalMaterial3Api::class) constructor(
    var containerColor: Color = Color.Unspecified,
    var scrolledContainerColor: Color = Color.Unspecified,
    var topbarType: TopbarType = TopbarType.NORMAL,
    var isStickyHeader: Boolean = false
)

data class BottomAppBar(
    var containerColor: Color = Color.Unspecified,
    var scrolledContainerColor: Color = Color.Unspecified,
    var shadowElevation: Dp = 4.dp,
    var tonalElevation: Dp = 0.dp,
)

data class NavigationRailAppBar(
    var color: Color = Color.Unspecified,
    var backGroundColor: Color = Color.Unspecified,
    var shadowElevation: Dp = 4.dp,
    var shape: Shape = RoundedCornerShape(12.dp),
    var tonalElevation: Dp = 0.dp,
    var padding: Dp = 16.dp

)

