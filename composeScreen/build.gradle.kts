import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.konan.target.HostManager

plugins {
    id("maven-publish")
    id("org.jetbrains.kotlin.multiplatform") version "2.2.20"
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.20"
    id("org.jetbrains.compose") version "1.9.0"
    id("com.android.library") version "8.13.0"
//    id("signing")

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
            // back handler
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

group = "com.github.Joshaghani.KmpComposeScreen"
version = "0.0.110"

//group = "io.github.mohammadjoshaghani" // همونی که Sonatype تایید کرد
//version = "0.0.1"

publishing {
    repositories {
        maven {
            name = "GhPages"
            url = uri(layout.projectDirectory.dir("gh-pages-maven")) // خروجی اینجاست
        }
    }

//    publications.withType<MavenPublication> {
//        pom {
//            name.set("composeScreen")
//            description.set("KMP ComposeScreen Library with iOS")
//            url.set("https://github.com/joshaghani/kmpcomposeScreen")
//
//            licenses {
//                license {
//                    name.set("Apache-2.0")
//                    url.set("https://www.apache.org/licenses/LICENSE-2.0")
//                }
//            }
//            scm {
//                connection.set("scm:git:git://github.com/joshaghani/kmpcomposeScreen.git")
//                developerConnection.set("scm:git:ssh://github.com/joshaghani/kmpcomposeScreen.git")
//                url.set("https://github.com/joshaghani/kmpcomposeScreen")
//            }
//            developers {
//                developer {
//                    id.set("mohammadjoshaghani")
//                    name.set("Mohammad Joshaghani")
//                }
//            }
//        }
//    }
}

//      ./gradlew :composeScreen:publishAllPublicationsToGhPagesRepository --stacktrace


// ./gradlew :composeScreen:publishAllPublicationsToGhPagesRepository \
//  --no-configuration-cache \
//  --refresh-dependencies