package ru.homecraft.glamapp.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.scene.rememberSceneSetupNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule


@Composable
fun Root(
    modifier: Modifier,
) {
    val backStack = rememberNavBackStack(configuration = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(
                baseClass = NavKey::class,
                actualClass = HomeScreen::class,
                actualSerializer = HomeScreen.serializer()
            )
            polymorphic(
                baseClass = NavKey::class,
                actualClass = ProfileScreen::class,
                actualSerializer = ProfileScreen.serializer()
            )
        }
    }, HomeScreen)


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            NavDisplay(
                modifier = modifier.fillMaxSize().padding(paddingValues),
                backStack = backStack,
                entryDecorators = listOf(
                    rememberSceneSetupNavEntryDecorator(),
                    rememberSavedStateNavEntryDecorator(),
                ),
                entryProvider = entryProvider {
                    entry<HomeScreen> {
                        HomeScreen(
                            toCreateOrderScreen = {
                                // TODO()
                            }
                        )
                    }
                    entry<ProfileScreen> {
                        ProfileScreen()
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.fillMaxWidth(),
            ) {
                NavigationBarItem(
                    selected = backStack.last() == HomeScreen,
                    onClick = {
                        backStack.add(HomeScreen)
                    },
                    icon = {
                        Icon(Icons.Default.Home, null)
                    }
                )
                NavigationBarItem(
                    selected = backStack.last() == ProfileScreen,
                    onClick = {
                        backStack.add(ProfileScreen)
                    },
                    icon = {
                        Icon(Icons.Default.AccountBox, null)
                    }
                )
            }
        }
    )

}




