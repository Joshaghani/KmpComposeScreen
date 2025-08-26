import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.konan.target.HostManager

plugins {
    id("maven-publish")
    id("org.jetbrains.kotlin.multiplatform") version "2.2.10"
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.10"
    id("org.jetbrains.compose") version "1.8.2"
    id("com.android.library") version "8.12.1"
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions { jvmTarget.set(JvmTarget.JVM_11) }
    }
    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.library()
        outputModuleName.set("KmpComposeScreen")
    }

    val iosTargets = listOf(iosX64(), iosArm64(), iosSimulatorArm64())
    if (HostManager.hostIsMac) {
        iosTargets.forEach { t ->
            t.binaries.framework {
                baseName = "composeScreen"
                isStatic = true
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.materialIconsExtended)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            api("dev.chrisbanes.material3:material3-window-size-class-multiplatform:0.5.0")
        }
    }
}

android {
    namespace = "com.github.mohammadjoshaghani.composescreen"
    compileSdk = 36
    defaultConfig { minSdk = 24 }
    buildFeatures { compose = true }
}

group = "com.github.Joshaghani.KmpComposeScreen"
version = "0.0.87"

publishing {
    repositories {
        maven {
            name = "GhPages"
            url = uri(layout.projectDirectory.dir("gh-pages-maven")) // خروجی اینجاست
        }
    }
}

//      ./gradlew :composeScreen:publishAllPublicationsToGhPagesRepository --stacktrace