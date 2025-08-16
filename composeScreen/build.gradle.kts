import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.gradle.internal.os.OperatingSystem

plugins {
    id("maven-publish")
    id("org.jetbrains.kotlin.multiplatform") version "2.2.10"
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.10"
    id("org.jetbrains.compose") version "1.8.2"
    id("com.android.library")
}

group = (project.findProperty("group") as String?) ?: "com.github.Joshaghani"
version = (project.findProperty("version") as String?) ?: "0.0.55"

val isMac = OperatingSystem.current().isMacOsX
val enableIos = isMac
kotlin {
    jvmToolchain(17)

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

    if (enableIos) {
        iosX64()
        iosArm64()
        iosSimulatorArm64()
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(compose.materialIconsExtended)

                implementation(libs.androidx.lifecycle.viewmodelCompose)
                implementation(libs.androidx.lifecycle.runtimeCompose)

                // Window Size Class مولتی‌پلتفرم
                api("dev.chrisbanes.material3:material3-window-size-class-multiplatform:0.5.0")
            }
        }

        if (enableIos) {
            val iosMain by getting
            val iosArm64Main by getting
            val iosX64Main by getting
            val iosSimulatorArm64Main by getting
        }
    }
}

android {
    namespace = "com.github.mohammadjoshaghani.composescreen"
    compileSdk = 36
    defaultConfig { minSdk = 24 }
    buildFeatures { compose = true }
}

publishing {
    publications.withType<MavenPublication>().configureEach {
        if (name == "kotlinMultiplatform") {
            artifactId = "composeScreen"
        }
        pom {
            name.set("KmpComposeScreen")
            description.set("Kotlin Multiplatform Compose helpers")
            url.set("https://github.com/Joshaghani/KmpComposeScreen")
        }
    }
    repositories {
        if (System.getenv("GITHUB_ACTOR") != null && System.getenv("GITHUB_TOKEN") != null) {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/Joshaghani/KmpComposeScreen")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}