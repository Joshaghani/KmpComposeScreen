package com.example.kmpcomposescreen.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

fun initKoin(block: KoinApplication.() -> Unit = {}) {
    startKoin {
        block()
        modules(SharedModule().module)
    }
}