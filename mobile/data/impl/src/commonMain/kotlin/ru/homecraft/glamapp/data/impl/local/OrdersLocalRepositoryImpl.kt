package ru.homecraft.glamapp.data.impl.local

import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single
import ru.homecraft.glamapp.data.api.local.OrderDao
import ru.homecraft.glamapp.data.api.local.OrdersLocalRepository
import ru.homecraft.glamapp.data.api.models.OrderEntity
import ru.homecraft.glamapp.utils.Logger

@Single(binds = [OrdersLocalRepository::class])
class OrdersLocalRepositoryImpl(
    private val orderDao: OrderDao
): OrdersLocalRepository {
    override val orders: Flow<List<OrderEntity>> = orderDao.getAllAsFlow

    override suspend fun add(orders: List<OrderEntity>) {
        Logger.suspendTrace("OrdersLocalRepositoryImpl") {
            orderDao.insertBatch(orders)
        }
    }
}