import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("maven-publish")
    id("org.jetbrains.kotlin.multiplatform") version "2.2.10"
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.10"
    id("org.jetbrains.compose") version "1.8.2"
    id("com.android.library")
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser() // config اختیاری
        binaries.library()
        outputModuleName.set("KmpComposeScreen")
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "composeScreen"
            isStatic = true
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

publishing {
    publications.withType<MavenPublication>().configureEach {
        pom {
            name.set("KmpComposeScreen")
            description.set("Compose Multiplatform UI components")
            url.set("https://github.com/Joshaghani/KmpComposeScreen")
            licenses { license {
                name.set("Apache-2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0")
            } }
            scm { url.set("https://github.com/Joshaghani/KmpComposeScreen") }
        }
    }
    repositories {
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


group = "com.github.Joshaghani.KmpComposeScreen"
version = "0.0.7۲"