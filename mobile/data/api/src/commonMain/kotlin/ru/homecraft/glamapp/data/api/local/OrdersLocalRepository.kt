package ru.homecraft.glamapp.data.api.local

import arrow.core.Either
import kotlinx.coroutines.flow.Flow
import ru.homecraft.glamapp.data.api.models.OrderEntity
import ru.homecraft.glamapp.utils.AppError

interface OrdersLocalRepository {
    val orders: Flow<List<OrderEntity>>

    suspend fun add(orders: List<OrderEntity>): Either<AppError, Unit>
}