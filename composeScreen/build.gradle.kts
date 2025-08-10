import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    id("maven-publish")
    id("org.jetbrains.kotlin.multiplatform") version "2.2.0"
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.0"
    id("org.jetbrains.compose") version "1.8.2"
    id("com.android.library")
}

kotlin {

    androidTarget()

    val xcfName = "composeScreenKit"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        outputModuleName.set("composeScreen")
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeScreen.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.library()

    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.stdlib)
            // Add KMP dependencies here
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(compose.materialIconsExtended)

            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // https://mvnrepository.com/artifact/androidx.compose.material3/material3-window-size-class
            api("dev.chrisbanes.material3:material3-window-size-class-multiplatform:0.5.0")
        }

    }


}

android {
    namespace = "com.github.mohammadjoshaghani.composescreen"
    compileSdk = 36
    defaultConfig {
        minSdk = 24
    }
    buildFeatures {
        compose = true
    }
}

group = (project.findProperty("group") as String?) ?: "com.github.Joshaghani"
version = (project.findProperty("version") as String?) ?: "0.0.1"

publishing {
    publications.withType<MavenPublication>().configureEach {
        artifactId = "KmpComposeScreen"
    }
}
