package ru.homecraft.glamapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.homecraft.glamapp.screens.Root
import ru.homecraft.glamapp.uikit.theme.GlamAppTheme

@Composable
@Preview
fun App() {
    GlamAppTheme {
        Root(modifier = Modifier.fillMaxSize())
    }
}