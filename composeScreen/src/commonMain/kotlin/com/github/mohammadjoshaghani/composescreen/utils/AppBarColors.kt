package com.github.mohammadjoshaghani.composescreen.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mohammadjoshaghani.composescreen.screen.scaffold.topBar.TopbarSizeType

data class TopAppBarConfig @OptIn(ExperimentalMaterial3Api::class) constructor(
    var containerColor: Color = Color.Unspecified,
    var scrolledContainerColor: Color = Color.Unspecified,
    var topbarSizeType: TopbarSizeType = TopbarSizeType.SMALL,
    var shadowElevationLight: Int = 5,
    var shadowElevationDark: Int = 1,
)

data class BottomAppBarConfig(
    var containerColor: Color = Color.Unspecified,
    var contentColor: Color = Color.Unspecified,
    var shadowElevationLight: Int = 5,
    var shadowElevationDark: Int = 1,
    var modifier: Modifier = Modifier
)

data class NavigationRailAppBarConfig(
    var color: Color = Color.Unspecified,
    var backGroundColor: Color = Color.Unspecified,
    var shadowElevation: Dp = 4.dp,
    var shape: Shape = RoundedCornerShape(12.dp),
    var tonalElevation: Dp = 0.dp,
    var padding: Dp = 16.dp

)

