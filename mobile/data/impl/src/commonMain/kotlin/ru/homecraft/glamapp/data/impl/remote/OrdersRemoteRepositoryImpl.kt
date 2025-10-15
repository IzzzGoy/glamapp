package ru.homecraft.glamapp.data.impl.remote

import arrow.core.Either
import arrow.core.raise.either
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.core.annotation.Factory
import ru.homecraft.glamapp.data.api.models.OrderData
import ru.homecraft.glamapp.data.api.models.OrderStatus
import ru.homecraft.glamapp.data.api.models.OrdersPageModel
import ru.homecraft.glamapp.data.api.remote.OrdersRemoteRepository
import ru.homecraft.glamapp.utils.AppError
import ru.homecraft.glamapp.utils.Logger
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Factory(binds = [OrdersRemoteRepository::class])
class OrdersRemoteRepositoryImpl: OrdersRemoteRepository {

    override suspend fun load(page: Int, size: Int): Either<AppError, OrdersPageModel> {
        return either {
            Logger.trace("OrdersRemoteRepositoryImpl") {
                val data = (0..15).map {
                    OrderData(
                        id = it,
                        status = OrderStatus.entries.random(),
                        createdAt = Clock.System.now().toLocalDateTime(TimeZone.UTC)
                    )
                }

                OrdersPageModel(
                    size = data.size,
                    pageData = data,
                )
            }
        }
    }
}