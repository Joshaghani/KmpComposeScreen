package com.github.mohammadjoshaghani.composescreen.screen.errorScreen

data class ErrorScreenMessageModel<Event>(
    val message: String,
    val event: Event,
)