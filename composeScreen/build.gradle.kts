import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.konan.target.HostManager

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("com.android.library")
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    id("com.vanniktech.maven.publish") version "0.36.0"
    id("signing")
}

group = "io.github.joshaghani"
version = "1.0.0-beta71"

kotlin {
    androidTarget()

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

    js {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.material)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.material.icons.extended)

            // Navigator
            api(libs.voyager.navigator)

            // Screen Model
            api(libs.voyager.screenmodel)

            // BottomSheetNavigator
            api(libs.voyager.bottom.sheet.navigator)

            // TabNavigator
            api(libs.voyager.tab.navigator)

            // Transitions
            api(libs.voyager.transitions)

            api("dev.chrisbanes.material3:material3-window-size-class-multiplatform:0.5.0")

        }
    }
}

android {
    namespace = "io.github.joshaghani.composescreen"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }


    buildFeatures {
        buildConfig = false
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    pom {
        name.set("composeScreen")
        description.set("A lightweight Compose Multiplatform screens library.")
        inceptionYear.set("2025")

        url.set("https://github.com/joshaghani/KmpComposeScreen")

        licenses {
            license {
                name.set("Apache License 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0")
                distribution.set("repo")
            }
        }
        developers {
            developer {
                id.set("joshaghani")
                name.set("Mohammad Joshaghani")
                url.set("https://github.com/joshaghani")
            }
        }
        scm {
            url.set("https://github.com/joshaghani/KmpComposeScreen")
            connection.set("scm:git:https://github.com/joshaghani/KmpComposeScreen.git")
            developerConnection.set("scm:git:ssh://git@github.com/joshaghani/KmpComposeScreen.git")
        }
    }
}

extensions.configure<SigningExtension>("signing") {
    val key = providers.gradleProperty("signingInMemoryKey").orNull
    val pass = providers.gradleProperty("signingInMemoryKeyPassword").orNull
    if (!key.isNullOrBlank() && !pass.isNullOrBlank()) {
        useInMemoryPgpKeys(key, pass)
        sign(publishing.publications)
    }
}
