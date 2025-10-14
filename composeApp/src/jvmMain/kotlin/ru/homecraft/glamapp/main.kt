package ru.homecraft.glamapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.core.annotation.KoinApplication
import org.koin.ksp.generated.configurationModules

@KoinApplication
object KoinApp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "glamapp",
    ) {
        org.koin.compose.KoinApplication(
            application = {
                modules(KoinApp.configurationModules)
            }
        ) {
            App()
        }
    }
}