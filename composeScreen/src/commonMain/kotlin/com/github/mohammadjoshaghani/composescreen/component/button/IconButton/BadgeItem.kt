package com.github.mohammadjoshaghani.composescreen.component.button.IconButton

sealed interface BadgeItem {
    data object None : BadgeItem
    data object Badge : BadgeItem
    data class BadgeWithNumber(val number: Int) : BadgeItem
}