package ru.homecraft.glamapp.data.api.local

import kotlinx.coroutines.flow.Flow
import ru.homecraft.glamapp.data.api.models.OrderEntity

interface OrdersLocalRepository {
    val orders: Flow<List<OrderEntity>>

    suspend fun add(orders: List<OrderEntity>)
}