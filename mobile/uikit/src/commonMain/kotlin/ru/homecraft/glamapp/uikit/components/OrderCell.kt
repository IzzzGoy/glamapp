package ru.homecraft.glamapp.uikit.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Api
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.homecraft.glamapp.uikit.theme.GlamAppTheme
import ru.homecraft.glamapp.uikit.theme.Paddings

@Immutable
data class OrderCellState(
    val icon: ImageVector,
    val title: String,
    val description: String? = null,
    val timeMark: String? = null,
)

@Composable
fun OrderCell(
    modifier: Modifier,
    state: OrderCellState,
    onClick: () -> Unit = {},
) {
    ElevatedCard(
        modifier = modifier.clickable {
            onClick()
        },
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(Paddings.lg),
            horizontalArrangement = Arrangement.spacedBy(Paddings.md),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                    ) {
                    Icon(
                        imageVector = state.icon,
                        contentDescription = null,
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = state.title,
                    style = MaterialTheme.typography.titleMedium,
                )

                if (state.description != null) {
                    Text(
                        text = state.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = .6f
                        ),
                    )
                }
            }
            if (state.timeMark != null) {
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = state.timeMark,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}


@Preview
@Composable
fun OrderCellDefaultPreview() {
    GlamAppTheme {
        OrderCell(
            modifier = Modifier.fillMaxWidth().height(height = 100.dp),
            state = OrderCellState(
                icon = Icons.Default.Api,
                title = "This is title",
            )
        )
    }
}

@Preview
@Composable
fun OrderCellDescriptionDefaultPreview() {
    GlamAppTheme {
        OrderCell(
            modifier = Modifier.fillMaxWidth().height(100.dp),
            state = OrderCellState(
                icon = Icons.Default.Api,
                title = "This is title",
                description = "This is description",
            )
        )
    }
}

@Preview
@Composable
fun OrderCellTimeMarkDefaultPreview() {
    GlamAppTheme {
        OrderCell(
            modifier = Modifier.fillMaxWidth().height(100.dp),
            state = OrderCellState(
                icon = Icons.Default.Api,
                title = "This is title",
                description = "This is description",
                timeMark = "22:00"
            )
        )
    }
}