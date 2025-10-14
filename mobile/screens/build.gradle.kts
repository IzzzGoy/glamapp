import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
}

kotlin {

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

/*    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }*/

    jvm()

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.material3)
                implementation(compose.uiTooling)
                implementation(compose.materialIconsExtended)
                implementation(projects.mobile.uikit)
                implementation(libs.navigation3.ui)
                implementation(libs.navigation3.runtime)

                implementation(libs.koin.compose.viewmodel)

                implementation(libs.kotlinx.serialization.json)

                implementation(projects.mobile.presentation.api)
                implementation(projects.mobile.utils)

                //implementation(libs.kotzilla.sdk)
            }
        }
    }
}

android {
    namespace = "ru.homecraft.glamapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}