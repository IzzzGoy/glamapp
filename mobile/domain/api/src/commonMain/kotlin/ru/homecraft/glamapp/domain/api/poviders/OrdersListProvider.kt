package ru.homecraft.glamapp.domain.api.poviders

import kotlinx.coroutines.flow.Flow
import ru.homecraft.glamapp.domain.api.model.OrderList

interface OrdersListProvider {
    val orders: Flow<OrderList>
}