package ru.homecraft.glamapp.data.impl.local

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.raise.either
import arrow.core.right
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single
import ru.homecraft.glamapp.data.api.local.OrderDao
import ru.homecraft.glamapp.data.api.local.OrdersLocalRepository
import ru.homecraft.glamapp.data.api.models.OrderEntity
import ru.homecraft.glamapp.utils.AppError
import ru.homecraft.glamapp.utils.DataError
import ru.homecraft.glamapp.utils.Logger

@Single(binds = [OrdersLocalRepository::class])
class OrdersLocalRepositoryImpl(
    private val orderDao: OrderDao
) : OrdersLocalRepository {
    override val orders: Flow<List<OrderEntity>> = orderDao.getAllAsFlow

    override suspend fun add(orders: List<OrderEntity>): Either<AppError, Unit> =
        Logger.suspendTrace("OrdersLocalRepositoryImpl - orders insertion") {
            either {
                catch(
                    {
                        orderDao.insertBatch(orders).right()
                    },
                    {
                        raise(DataError.LocalDataError.DatabaseWrightError(it.message.orEmpty()))
                    }
                )

            }
        }
}