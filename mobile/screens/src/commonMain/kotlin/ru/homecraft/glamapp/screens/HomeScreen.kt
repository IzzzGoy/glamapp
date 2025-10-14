package ru.homecraft.glamapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel
import ru.homecraft.glamapp.presentation.api.HomeViewModel
import ru.homecraft.glamapp.uikit.components.OrderCellState
import ru.homecraft.glamapp.uikit.layout.OrdersList
import ru.homecraft.glamapp.uikit.layout.OrdersListItem
import ru.homecraft.glamapp.uikit.theme.Paddings
import ru.homecraft.glamapp.utils.Logger

@Serializable
data object HomeScreen : NavKey


@Composable
fun HomeScreen(
    toCreateOrderScreen: () -> Unit,
) {
    val viewModel = koinViewModel<HomeViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effects.collect {
            Logger.log("New effect on Home screen: $it")
            when(it) {
                HomeViewModel.HomeViewModelEffect.GoToCreateOrder -> {
                    Logger.trace("Navigation") {
                        toCreateOrderScreen()
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        OrdersList(
            orders = state.items.map { key ->
                OrdersListItem(
                    key = key,
                    OrderCellState(
                        icon = Icons.Default.AddCard,
                        title = "Test: $key",
                    )
                )
            },
            bottomPadding = 60.dp
        )
        ElevatedButton(
            onClick = {
                Logger.log("On create order click")
                viewModel.dispatch(HomeViewModel.HomeViewModelAction.OnCreateOrderClick)
            },
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 4.dp,
            ),
            modifier = Modifier
                .padding(Paddings.sm)
                .fillMaxWidth()
                .height(60.dp)
                .align(Alignment.BottomCenter)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
            )
        }
    }
}
