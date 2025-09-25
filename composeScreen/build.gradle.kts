import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.konan.target.HostManager

plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.2.20"
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.20"
    id("org.jetbrains.compose") version "1.9.0"
    id("com.android.library") version "8.13.0"
    id("maven-publish")
    id("signing")
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
            implementation(libs.ui.backhandler)
        }
    }
}

android {
    namespace = "com.github.mohammadjoshaghani.composescreen"
    compileSdk = 36
    defaultConfig { minSdk = 24 }
    buildFeatures { compose = true }
}

group = "io.github.joshaghani"
version = "1.0.0-beta4"

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set("KmpComposeScreen")
            description.set("Kotlin Multiplatform Compose library (Android + iOS)")
            url.set("https://github.com/Joshaghani/KmpComposeScreen")

            licenses {
                license {
                    name.set("Apache-2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0")
                }
            }
            scm {
                connection.set("scm:git:https://github.com/Joshaghani/KmpComposeScreen.git")
                developerConnection.set("scm:git:ssh://github.com/Joshaghani/KmpComposeScreen.git")
                url.set("https://github.com/Joshaghani/KmpComposeScreen")
            }
            developers {
                developer {
                    id.set("joshaghani")
                    name.set("Mohammad Joshaghani")
                }
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
        System.getenv("SIGNING_KEY"),
        System.getenv("SIGNING_PASSWORD")
    )
    sign(publishing.publications)
}