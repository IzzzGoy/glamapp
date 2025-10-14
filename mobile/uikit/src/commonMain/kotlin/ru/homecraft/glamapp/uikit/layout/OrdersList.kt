package ru.homecraft.glamapp.uikit.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.homecraft.glamapp.uikit.components.OrderCell
import ru.homecraft.glamapp.uikit.components.OrderCellState
import ru.homecraft.glamapp.uikit.theme.GlamAppTheme
import ru.homecraft.glamapp.uikit.theme.Paddings

@Immutable
data class OrdersListItem<T: Any>(
    val key: T,
    val orderCellState: OrderCellState,
)

@Composable
fun<T: Any> OrdersList(
    orders: List<OrdersListItem<T>>,
    modifier: Modifier = Modifier,
    bottomPadding: Dp = 0.dp
) {
    val state = rememberLazyListState()
    LazyColumn(
        modifier = modifier.background(
            MaterialTheme.colorScheme.background,
        ),
        state = state,
        verticalArrangement = Arrangement.spacedBy(Paddings.smVertical),
        contentPadding = PaddingValues(
            start = Paddings.smHorizontal,
            end = Paddings.smHorizontal,
            top = Paddings.smVertical,
            bottom = Paddings.smVertical + bottomPadding),
    ) {
        items(orders, key = { it.key }) { order ->
            OrderCell(
                modifier = Modifier.height(100.dp),
                state = order.orderCellState,
                onClick = {
                    // TODO
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrdersListPreview() {
    GlamAppTheme {
        OrdersList(
            orders = listOf(
                OrdersListItem(
                    key = 1,
                    orderCellState = OrderCellState(
                        icon = Icons.Default.AddCard,
                        title = "First item"
                    )
                ),
                OrdersListItem(
                    key = 2,
                    orderCellState = OrderCellState(
                        icon = Icons.Default.AddCard,
                        title = "Second item",
                        description = "Description",
                    )
                ),
                OrdersListItem(
                    key = 3,
                    orderCellState = OrderCellState(
                        icon = Icons.Default.AddCard,
                        title = "Second item",
                        description = "Description",
                        timeMark = "22:16",
                    )
                )
            )
        )
    }
}
